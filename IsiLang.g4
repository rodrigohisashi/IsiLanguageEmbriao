grammar IsiLang;

@header{
	import br.com.professorisidro.isilanguage.datastructures.IsiSymbol;
	import br.com.professorisidro.isilanguage.datastructures.IsiVariable;
	import br.com.professorisidro.isilanguage.datastructures.IsiSymbolTable;
	import br.com.professorisidro.isilanguage.exceptions.IsiSemanticException;
	import br.com.professorisidro.isilanguage.ast.IsiProgram;
	import br.com.professorisidro.isilanguage.ast.AbstractCommand;
	import br.com.professorisidro.isilanguage.ast.CommandLeitura;
	import br.com.professorisidro.isilanguage.ast.CommandEscrita;
	import br.com.professorisidro.isilanguage.ast.CommandAtribuicao;
	import br.com.professorisidro.isilanguage.ast.CommandDecisao;
	import br.com.professorisidro.isilanguage.ast.CommandRepeticao;
	import br.com.professorisidro.isilanguage.ast.CommandEscolha;
	import java.util.ArrayList;
	import java.util.Stack;
}

@members{
	private int _tipo;
	private String _varName;
	private String _varValue;
	private IsiSymbolTable symbolTable = new IsiSymbolTable();
	private IsiSymbol symbol;
	private IsiVariable var;
	private IsiProgram program = new IsiProgram();
	private ArrayList<AbstractCommand> curThread;
	private Stack<ArrayList<AbstractCommand>> stack = new Stack<ArrayList<AbstractCommand>>();
	private String _readID;
	private String _writeID;
	private String _exprID;
	private String _repID;
	private String _decID;
	private String _exprContent;
	private String _exprDecision;
	private String _exprRepetition;
	private String _exprSwitch;
	private ArrayList<String> notUsed = new ArrayList<String>();
	private ArrayList<String> condicao = new ArrayList<String>();
	private ArrayList<AbstractCommand> listaTrue;
	private ArrayList<AbstractCommand> listaFalse;
	private ArrayList<AbstractCommand> listaBloco;
	private ArrayList<ArrayList<AbstractCommand>> casos;

	public void verificaID(String id){
		if (!symbolTable.exists(id)){
			throw new IsiSemanticException("Symbol "+id+" not declared");
		}
	}

	public void verificaUtil(){
	    for (String s: notUsed) {
	        System.out.println("Warning: Variable " + s + " was declared but never used!");
	    }
	}

	public String textoTipo(int type) {
	       String str;
           if (type == IsiVariable.INTEGER) {
               str = "INTEGER";
           }
           else if (type == IsiVariable.DOUBLE) {
               str = "DOUBLE";
           }
           else if (type == IsiVariable.BOOL) {
               str = "BOOL";
           }
           else if (type == IsiVariable.CHAR) {
               str = "CHAR";
           }
           else {
               str = "TEXT";
           }
           return str;
	}
	
	public void exibeComandos(){
		for (AbstractCommand c: program.getComandos()){
			System.out.println(c);
		}
	}
	
	public void generateCode(){
		program.generateTarget();
		program.generateTargetCpp();
	}
}

prog	: 'programa' decl bloco  'fimprog;'
           {  program.setVarTable(symbolTable);
           	  program.setComandos(stack.pop());
           	 
           } 
		;
		
decl    :  (declaravar)+
        ;
        
        
declaravar :  tipo ID  {
	                  _varName = _input.LT(-1).getText();
	                  _varValue = null;
	                  symbol = new IsiVariable(_varName, _tipo, _varValue);
	                  if (!symbolTable.exists(_varName)){
	                     symbolTable.add(symbol);
	                     notUsed.add(_varName);
	                  }
	                  else{
	                  	 throw new IsiSemanticException("Symbol "+_varName+" already declared");
	                  }
                    } 
              (  VIR 
              	 ID {
	                  _varName = _input.LT(-1).getText();
	                  _varValue = null;
	                  symbol = new IsiVariable(_varName, _tipo, _varValue);
	                  if (!symbolTable.exists(_varName)){
	                     symbolTable.add(symbol);
	                     notUsed.add(_varName);
	                  }
	                  else{
	                  	 throw new IsiSemanticException("Symbol "+_varName+" already declared");
	                  }
                    }
              )* 
               SC
           ;
           
tipo       : 'inteiro'   { _tipo = IsiVariable.INTEGER;}
           | 'texto'     { _tipo = IsiVariable.TEXT;   }
           | 'real'      { _tipo = IsiVariable.DOUBLE; }
           | 'caractere' { _tipo = IsiVariable.CHAR;   }
           | 'booleano'  { _tipo = IsiVariable.BOOL;   }
           ;
        
bloco	: { curThread = new ArrayList<AbstractCommand>(); 
	        stack.push(curThread);  
          }
          (cmd)+
		;
		

cmd		:  cmdleitura  
 		|  cmdescrita 
 		|  cmdattrib
 		|  cmdselecao
 		|  cmdrepeticao
 		|  cmdescolha
		;

		
cmdleitura	: 'leia' AP
                     ID { verificaID(_input.LT(-1).getText());
                     	  _readID = _input.LT(-1).getText();
                     	  if (notUsed.contains(_readID)) notUsed.remove(_readID);
                        } 
                     FP 
                     SC 
                     
              {
              	IsiVariable var = (IsiVariable)symbolTable.get(_readID);
              	CommandLeitura cmd = new CommandLeitura(_readID, var);
              	stack.peek().add(cmd);
              }   
			;
			
cmdescrita	: 'escreva' 
                 AP 
                 ID { verificaID(_input.LT(-1).getText());
	                  _writeID = _input.LT(-1).getText();
	                  if (notUsed.contains(_writeID)) notUsed.remove(_writeID);
                     } 
                 FP 
                 SC
               {
               	  CommandEscrita cmd = new CommandEscrita(_writeID);
               	  stack.peek().add(cmd);
               }
			;
			
cmdattrib	:  ID { verificaID(_input.LT(-1).getText());
                    _exprID = _input.LT(-1).getText();
                    if (notUsed.contains(_exprID)) notUsed.remove(_exprID);
                    var = (IsiVariable)symbolTable.get(_exprID);
                    _tipo = var.getType();
                   } 
               ATTR { _exprContent = ""; } 
               expr 
               SC
               {


                 var.setValue(_exprContent);
                 symbolTable.attributeValue(var.getName(), var);

               	 CommandAtribuicao cmd = new CommandAtribuicao(_exprID, _exprContent);
               	 stack.peek().add(cmd);
               }
			;

cmdescolha : 'escolha'
              AP
              ID {
                   verificaID(_input.LT(-1).getText());
                   _decID = _input.LT(-1).getText();
                   _exprSwitch = _input.LT(-1).getText();
                   if (notUsed.contains(_decID)) notUsed.remove(_decID);
                   casos = new ArrayList<ArrayList<AbstractCommand>>();
                   condicao.clear();
                   listaBloco = new ArrayList<AbstractCommand>();
                   CommandEscolha cmd = new CommandEscolha(_exprSwitch, casos, condicao, listaBloco);
                 }
              FP
              ACH
              (
              'caso'
              (ID {
                 verificaID(_input.LT(-1).getText());
              } | INTEGER | DOUBLE | TEXT | CHAR | BOOL)
              {
                 _decID = _input.LT(-1).getText();
                 condicao.add(_input.LT(-1).getText());
                 cmd.setCondicao(condicao);
                 if (notUsed.contains(_decID)) notUsed.remove(_decID);
              }
              ':'
              {
                curThread = new ArrayList<AbstractCommand>();
                stack.push(curThread);
              }
              (cmd)+
              {
                casos.add(stack.pop());
                cmd.setCasos(casos);
              }
              )*
              (
              'padrao'
              ':'
              {
                curThread = new ArrayList<AbstractCommand>();
                stack.push(curThread);
              }
              (cmd)+
              {
                listaBloco = stack.pop();
                cmd.setPadrao(listaBloco);
              }
              )?
              FCH
              {

                stack.peek().add(cmd);
              }
           ;

cmdrepeticao: 'enquanto' AP
                         ID    { verificaID(_input.LT(-1).getText());
                                 _repID = _input.LT(-1).getText();
                                 _exprRepetition = _input.LT(-1).getText();
                                 if (notUsed.contains(_repID)) notUsed.remove(_repID);}
                         OPREL { _exprRepetition += _input.LT(-1).getText(); }
                         (ID
                         {
                            verificaID(_input.LT(-1).getText());
                         }
                         | INTEGER | DOUBLE)
                         {
                            _repID = _input.LT(-1).getText();
                            _exprRepetition += _input.LT(-1).getText();
                            if (notUsed.contains(_repID)) notUsed.remove(_repID);
                         }
                         FP
                         ACH
                         { curThread = new ArrayList<AbstractCommand>();
                               stack.push(curThread);
                         }
                          (cmd)+
                          FCH
                          {
                            listaBloco = stack.pop();
                            CommandRepeticao cmd = new CommandRepeticao(_exprRepetition, listaBloco);
                            stack.peek().add(cmd);
                          }
                         ;


cmdselecao  :  'se' AP
                    ID    {
                            verificaID(_input.LT(-1).getText());
                            _decID = _input.LT(-1).getText();
                            _exprDecision = _input.LT(-1).getText();
                            if (notUsed.contains(_decID)) notUsed.remove(_decID);
                            }
                    OPREL { _exprDecision += _input.LT(-1).getText(); }
                    (ID
                    {
                        verificaID(_input.LT(-1).getText());
                    }
                    | INTEGER | DOUBLE)
                    {
                        _decID = _input.LT(-1).getText();
                        _exprDecision += _input.LT(-1).getText();
                        if (notUsed.contains(_decID)) notUsed.remove(_decID);
                    }
                    FP
                    'entao'
                    ACH 
                    { curThread = new ArrayList<AbstractCommand>(); 
                      stack.push(curThread);
                    }
                    (cmd)+ 
                    
                    FCH 
                    {
                       listaTrue = stack.pop();
                       CommandDecisao cmd = new CommandDecisao(_exprDecision, listaTrue, new ArrayList<AbstractCommand>());
                    } 
                   ('senao' 
                   	 ACH
                   	 {
                   	 	curThread = new ArrayList<AbstractCommand>();
                   	 	stack.push(curThread);
                   	 } 
                   	(cmd+) 
                   	FCH
                   	{
                   		listaFalse = stack.pop();
                   		cmd.setListaFalse(listaFalse);

                   	}
                   )?
                   {
                     stack.peek().add(cmd);
                   }
            ;

expr        : exprmat
            | exprtext
            | exprbool
            {
                if (_tipo != IsiVariable.BOOL) throw new IsiSemanticException("Data type error in variable "
                + var.getName() + " : expected a " + textoTipo(_tipo) + " but got a BOOL.");
            }
            ;

exprmat		:
                  termo  (
	             OP  { _exprContent += _input.LT(-1).getText();}
	              expr
	            )*
                 |
                   AP { _exprContent += _input.LT(-1).getText();}
                   (
                   expr
                   AP { _exprContent += _input.LT(-1).getText();}
                   )*
                   expr
                   (
                   FP { _exprContent += _input.LT(-1).getText();}
                   expr
                   )*
                   FP { _exprContent += _input.LT(-1).getText();}
                   (
                   OP { _exprContent += _input.LT(-1).getText();}
                   expr
                   )*
			    ;

exprtext        : TEXT
                {
                  if (_tipo != IsiVariable.TEXT) throw new IsiSemanticException("Data type error in variable "
                    + var.getName() + " : expected a " + textoTipo(_tipo) + " but got a TEXT.");
                  _exprContent += _input.LT(-1).getText();
                }
                | CHAR
                {
                  if (_tipo != IsiVariable.TEXT && _tipo != IsiVariable.CHAR)
                  throw new IsiSemanticException("Data type error in variable "
                    + var.getName() + " : expected a " + textoTipo(_tipo) + " but got a CHAR.");
                  _exprContent += _input.LT(-1).getText();
                }
                ;

exprbool        : BOOL { _exprContent += _input.LT(-1).getText(); }
                | ID
                {
                    verificaID(_input.LT(-1).getText());
                    _decID = _input.LT(-1).getText();
                    _exprContent = _input.LT(-1).getText();
                    if (notUsed.contains(_decID)) notUsed.remove(_decID);
                }
                OPREL { _exprContent += _input.LT(-1).getText(); }
                (ID
                {
                  verificaID(_input.LT(-1).getText());
                }
                | INTEGER | DOUBLE)
                {
                  _decID = _input.LT(-1).getText();
                  _exprContent += _input.LT(-1).getText();
                  if (notUsed.contains(_decID)) notUsed.remove(_decID);
                }
                ;

termo		: ID { verificaID(_input.LT(-1).getText());
                   _varName = _input.LT(-1).getText();
	               IsiVariable varAttr = (IsiVariable)symbolTable.get(_input.LT(-1).getText());
	               if (_tipo == IsiVariable.DOUBLE) {
	                    if (_tipo != varAttr.getType() && varAttr.getType() != IsiVariable.INTEGER) throw new IsiSemanticException("Data type error in variable "
                           + var.getName() + " : expected a " + textoTipo(_tipo) + " but got a "+ textoTipo(varAttr.getType()) +".");
	               }
	               else if (_tipo == IsiVariable.TEXT) {
	                    if (_tipo != varAttr.getType() && varAttr.getType() != IsiVariable.CHAR) throw new IsiSemanticException("Data type error in variable "
                           + var.getName() + " : expected a " + textoTipo(_tipo) + " but got a "+ textoTipo(varAttr.getType()) +".");
	               }
	               else if (_tipo == IsiVariable.CHAR) {
	                    if (_tipo != varAttr.getType() && varAttr.getType() != IsiVariable.INTEGER) throw new IsiSemanticException("Data type error in variable "
                           + var.getName() + " : expected a " + textoTipo(_tipo) + " but got a "+ textoTipo(varAttr.getType()) +".");
	               }
	               else {
	                    if (_tipo != varAttr.getType()) throw new IsiSemanticException("Data type error in variable "
                           + var.getName() + " : expected a " + textoTipo(_tipo) + " but got a "+ textoTipo(varAttr.getType()) +".");
	               }
                   if (notUsed.contains(_input.LT(-1).getText())) notUsed.remove(_input.LT(-1).getText());
                   _exprContent += _input.LT(-1).getText();
                 } 
            | 
              INTEGER
              {
                if(_tipo != IsiVariable.INTEGER && _tipo != IsiVariable.DOUBLE && _tipo != IsiVariable.CHAR)
                throw new IsiSemanticException("Data type error in variable "
                    + var.getName() + " : expected a " + textoTipo(_tipo) + " but got a INTEGER.");
              	_exprContent += _input.LT(-1).getText();
              }
            |
              DOUBLE
              {
                if(_tipo != IsiVariable.DOUBLE) throw new IsiSemanticException("Data type error in variable "
                  + var.getName() + " : expected a " + textoTipo(_tipo) + " but got a INTEGER.");;
                _exprContent += _input.LT(-1).getText();
              }
			;

AP	: '('
	;
	
FP	: ')'
	;

ASP : '"'
    ;

DP : ':'
   ;
SC	: '.'
	;
	
OP	: '+' | '-' | '*' | '/'
	;
	
ATTR : ':='
	 ;
	 
VIR  : ','
     ;
     
ACH  : '{'
     ;
     
FCH  : '}'
     ;

OPLOG : '&&' | '||' | '!'
      ;

OPREL : '>' | '<' | '>=' | '<=' | '==' | '!='
      ;

BOOL    : 'true'
        | 'false'
        ;

ID	: [a-z] ([a-z] | [A-Z] | [0-9])*
	;

INTEGER	: [0-9]+
		;

DOUBLE	: [0-9]+ ('.' [0-9]+)?
		;

TEXT    : ASP ~ ["\r\n]* ASP
        ;

CHAR    : '\'' ~['\\\r\n] '\''
        ;

WS	: (' ' | '\t' | '\n' | '\r') -> skip;