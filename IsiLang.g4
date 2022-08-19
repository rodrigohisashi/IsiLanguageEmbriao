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
	private ArrayList<String> notUsed = new ArrayList<String>();
	private ArrayList<AbstractCommand> listaTrue;
	private ArrayList<AbstractCommand> listaFalse;
	private ArrayList<AbstractCommand> listaBloco;

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
	
	public void exibeComandos(){
		for (AbstractCommand c: program.getComandos()){
			System.out.println(c);
		}
	}
	
	public void generateCode(){
		program.generateTarget();
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
           
tipo       : 'numero' { _tipo = IsiVariable.NUMBER;  }
           | 'texto'  { _tipo = IsiVariable.TEXT;  }
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
			

cmdrepeticao: 'enquanto' AP
                         ID    { _repID = _input.LT(-1).getText();
                                 _exprRepetition = _input.LT(-1).getText();
                                 if (notUsed.contains(_repID)) notUsed.remove(_repID);}
                         OPREL { _exprRepetition += _input.LT(-1).getText(); }
                         (ID | NUMBER) {
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
                            _decID = _input.LT(-1).getText();
                            _exprDecision = _input.LT(-1).getText();
                            if (notUsed.contains(_decID)) notUsed.remove(_decID);
                            }
                    OPREL { _exprDecision += _input.LT(-1).getText(); }
                    (ID | NUMBER) {
                            _decID = _input.LT(-1).getText();
                            _exprDecision = _input.LT(-1).getText();
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
                   		CommandDecisao cmd = new CommandDecisao(_exprDecision, listaTrue, listaFalse);
                   		stack.peek().add(cmd);
                   	}
                   )?
            ;
			
expr		:
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
                   |
                   TEXT
                   {
                       if (_tipo == 0) throw new IsiSemanticException("Data type error in variable " + var.getName() + " : expected a number but got a string.");
                       _exprContent += _input.LT(-1).getText();
                   }
			    ;
			
termo		: ID { verificaID(_input.LT(-1).getText());
                   _varName = _input.LT(-1).getText();
	               IsiVariable varAttr = (IsiVariable)symbolTable.get(_input.LT(-1).getText());
	               if (_tipo == 0 && _tipo != varAttr.getType()) {
	                    throw new IsiSemanticException("Data type error in variable " + var.getName() + " : expected a number but got a string.");
	               }
	               if (_tipo == 1 && _tipo != varAttr.getType()) {
	                    throw new IsiSemanticException("Data type error in variable " + var.getName() + " : expected a string but got a number.");
	               }
                   if (notUsed.contains(_input.LT(-1).getText())) notUsed.remove(_input.LT(-1).getText());
                   _exprContent += _input.LT(-1).getText();
                 } 
            | 
              NUMBER
              {
                if(_tipo == 1) throw new IsiSemanticException("Data type error in variable " + var.getName() + " : expected a string but got a number.");
              	_exprContent += _input.LT(-1).getText();
              }
			;

AP	: '('
	;

ASP : '"'
    ;
	
FP	: ')'
	;
	
SC	: '.'
	;
	
OP	: '+' | '-' | '*' | '/'
	;

CONC : '+'
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
      
ID	: [a-z] ([a-z] | [A-Z] | [0-9])*
	;
	
NUMBER	: [0-9]+ ('.' [0-9]+)?
		;

INTEGER	: [0-9]+
		;

DOUBLE	: [0-9]+ ('.' [0-9]+)?
		;

exprbol :
        ;

BOOL    : 'True'
          |
          'False'
        ;

TEXT    : ASP ([a-z] | [A-Z] | [0-9] | WS | SP | AC)*? ASP
        ;

SP      : [!-_]
        ;

AC      : ('~' | '{' | '}' | '´' | '`')
        ;

WS	: (' ' | '\t' | '\n' | '\r') -> skip;