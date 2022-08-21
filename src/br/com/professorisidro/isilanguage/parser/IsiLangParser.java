// Generated from IsiLang.g4 by ANTLR 4.7.1
package br.com.professorisidro.isilanguage.parser;

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

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class IsiLangParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, AP=17, 
		FP=18, ASP=19, DP=20, SC=21, OP=22, ATTR=23, VIR=24, ACH=25, FCH=26, OPLOG=27, 
		OPREL=28, BOOL=29, ID=30, INTEGER=31, DOUBLE=32, TEXT=33, CHAR=34, WS=35;
	public static final int
		RULE_prog = 0, RULE_decl = 1, RULE_declaravar = 2, RULE_tipo = 3, RULE_bloco = 4, 
		RULE_cmd = 5, RULE_cmdleitura = 6, RULE_cmdescrita = 7, RULE_cmdattrib = 8, 
		RULE_cmdescolha = 9, RULE_cmdrepeticao = 10, RULE_cmdselecao = 11, RULE_expr = 12, 
		RULE_exprmat = 13, RULE_exprtext = 14, RULE_exprbool = 15, RULE_termo = 16;
	public static final String[] ruleNames = {
		"prog", "decl", "declaravar", "tipo", "bloco", "cmd", "cmdleitura", "cmdescrita", 
		"cmdattrib", "cmdescolha", "cmdrepeticao", "cmdselecao", "expr", "exprmat", 
		"exprtext", "exprbool", "termo"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'programa'", "'fimprog;'", "'inteiro'", "'texto'", "'real'", "'caractere'", 
		"'booleano'", "'leia'", "'escreva'", "'escolha'", "'caso'", "'padrao'", 
		"'enquanto'", "'se'", "'entao'", "'senao'", "'('", "')'", "'\"'", "':'", 
		"'.'", null, "':='", "','", "'{'", "'}'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, "AP", "FP", "ASP", "DP", "SC", "OP", "ATTR", 
		"VIR", "ACH", "FCH", "OPLOG", "OPREL", "BOOL", "ID", "INTEGER", "DOUBLE", 
		"TEXT", "CHAR", "WS"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "IsiLang.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }


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

	public IsiLangParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ProgContext extends ParserRuleContext {
		public DeclContext decl() {
			return getRuleContext(DeclContext.class,0);
		}
		public BlocoContext bloco() {
			return getRuleContext(BlocoContext.class,0);
		}
		public ProgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_prog; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterProg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitProg(this);
		}
	}

	public final ProgContext prog() throws RecognitionException {
		ProgContext _localctx = new ProgContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_prog);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(34);
			match(T__0);
			setState(35);
			decl();
			setState(36);
			bloco();
			setState(37);
			match(T__1);
			  program.setVarTable(symbolTable);
			           	  program.setComandos(stack.pop());
			           	 
			           
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DeclContext extends ParserRuleContext {
		public List<DeclaravarContext> declaravar() {
			return getRuleContexts(DeclaravarContext.class);
		}
		public DeclaravarContext declaravar(int i) {
			return getRuleContext(DeclaravarContext.class,i);
		}
		public DeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_decl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitDecl(this);
		}
	}

	public final DeclContext decl() throws RecognitionException {
		DeclContext _localctx = new DeclContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_decl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(41); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(40);
				declaravar();
				}
				}
				setState(43); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__3) | (1L << T__4) | (1L << T__5) | (1L << T__6))) != 0) );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DeclaravarContext extends ParserRuleContext {
		public TipoContext tipo() {
			return getRuleContext(TipoContext.class,0);
		}
		public List<TerminalNode> ID() { return getTokens(IsiLangParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(IsiLangParser.ID, i);
		}
		public TerminalNode SC() { return getToken(IsiLangParser.SC, 0); }
		public List<TerminalNode> VIR() { return getTokens(IsiLangParser.VIR); }
		public TerminalNode VIR(int i) {
			return getToken(IsiLangParser.VIR, i);
		}
		public DeclaravarContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declaravar; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterDeclaravar(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitDeclaravar(this);
		}
	}

	public final DeclaravarContext declaravar() throws RecognitionException {
		DeclaravarContext _localctx = new DeclaravarContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_declaravar);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(45);
			tipo();
			setState(46);
			match(ID);

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
			                    
			setState(53);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==VIR) {
				{
				{
				setState(48);
				match(VIR);
				setState(49);
				match(ID);

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
				}
				setState(55);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(56);
			match(SC);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TipoContext extends ParserRuleContext {
		public TipoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tipo; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterTipo(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitTipo(this);
		}
	}

	public final TipoContext tipo() throws RecognitionException {
		TipoContext _localctx = new TipoContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_tipo);
		try {
			setState(68);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__2:
				enterOuterAlt(_localctx, 1);
				{
				setState(58);
				match(T__2);
				 _tipo = IsiVariable.INTEGER;
				}
				break;
			case T__3:
				enterOuterAlt(_localctx, 2);
				{
				setState(60);
				match(T__3);
				 _tipo = IsiVariable.TEXT;   
				}
				break;
			case T__4:
				enterOuterAlt(_localctx, 3);
				{
				setState(62);
				match(T__4);
				 _tipo = IsiVariable.DOUBLE; 
				}
				break;
			case T__5:
				enterOuterAlt(_localctx, 4);
				{
				setState(64);
				match(T__5);
				 _tipo = IsiVariable.CHAR;   
				}
				break;
			case T__6:
				enterOuterAlt(_localctx, 5);
				{
				setState(66);
				match(T__6);
				 _tipo = IsiVariable.BOOL;   
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BlocoContext extends ParserRuleContext {
		public List<CmdContext> cmd() {
			return getRuleContexts(CmdContext.class);
		}
		public CmdContext cmd(int i) {
			return getRuleContext(CmdContext.class,i);
		}
		public BlocoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bloco; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterBloco(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitBloco(this);
		}
	}

	public final BlocoContext bloco() throws RecognitionException {
		BlocoContext _localctx = new BlocoContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_bloco);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			 curThread = new ArrayList<AbstractCommand>(); 
				        stack.push(curThread);  
			          
			setState(72); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(71);
				cmd();
				}
				}
				setState(74); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << T__12) | (1L << T__13) | (1L << ID))) != 0) );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CmdContext extends ParserRuleContext {
		public CmdleituraContext cmdleitura() {
			return getRuleContext(CmdleituraContext.class,0);
		}
		public CmdescritaContext cmdescrita() {
			return getRuleContext(CmdescritaContext.class,0);
		}
		public CmdattribContext cmdattrib() {
			return getRuleContext(CmdattribContext.class,0);
		}
		public CmdselecaoContext cmdselecao() {
			return getRuleContext(CmdselecaoContext.class,0);
		}
		public CmdrepeticaoContext cmdrepeticao() {
			return getRuleContext(CmdrepeticaoContext.class,0);
		}
		public CmdescolhaContext cmdescolha() {
			return getRuleContext(CmdescolhaContext.class,0);
		}
		public CmdContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmd; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterCmd(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitCmd(this);
		}
	}

	public final CmdContext cmd() throws RecognitionException {
		CmdContext _localctx = new CmdContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_cmd);
		try {
			setState(82);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__7:
				enterOuterAlt(_localctx, 1);
				{
				setState(76);
				cmdleitura();
				}
				break;
			case T__8:
				enterOuterAlt(_localctx, 2);
				{
				setState(77);
				cmdescrita();
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 3);
				{
				setState(78);
				cmdattrib();
				}
				break;
			case T__13:
				enterOuterAlt(_localctx, 4);
				{
				setState(79);
				cmdselecao();
				}
				break;
			case T__12:
				enterOuterAlt(_localctx, 5);
				{
				setState(80);
				cmdrepeticao();
				}
				break;
			case T__9:
				enterOuterAlt(_localctx, 6);
				{
				setState(81);
				cmdescolha();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CmdleituraContext extends ParserRuleContext {
		public TerminalNode AP() { return getToken(IsiLangParser.AP, 0); }
		public TerminalNode ID() { return getToken(IsiLangParser.ID, 0); }
		public TerminalNode FP() { return getToken(IsiLangParser.FP, 0); }
		public TerminalNode SC() { return getToken(IsiLangParser.SC, 0); }
		public CmdleituraContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdleitura; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterCmdleitura(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitCmdleitura(this);
		}
	}

	public final CmdleituraContext cmdleitura() throws RecognitionException {
		CmdleituraContext _localctx = new CmdleituraContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_cmdleitura);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(84);
			match(T__7);
			setState(85);
			match(AP);
			setState(86);
			match(ID);
			 verificaID(_input.LT(-1).getText());
			                     	  _readID = _input.LT(-1).getText();
			                     	  if (notUsed.contains(_readID)) notUsed.remove(_readID);
			                        
			setState(88);
			match(FP);
			setState(89);
			match(SC);

			              	IsiVariable var = (IsiVariable)symbolTable.get(_readID);
			              	CommandLeitura cmd = new CommandLeitura(_readID, var);
			              	stack.peek().add(cmd);
			              
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CmdescritaContext extends ParserRuleContext {
		public TerminalNode AP() { return getToken(IsiLangParser.AP, 0); }
		public TerminalNode ID() { return getToken(IsiLangParser.ID, 0); }
		public TerminalNode FP() { return getToken(IsiLangParser.FP, 0); }
		public TerminalNode SC() { return getToken(IsiLangParser.SC, 0); }
		public CmdescritaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdescrita; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterCmdescrita(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitCmdescrita(this);
		}
	}

	public final CmdescritaContext cmdescrita() throws RecognitionException {
		CmdescritaContext _localctx = new CmdescritaContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_cmdescrita);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(92);
			match(T__8);
			setState(93);
			match(AP);
			setState(94);
			match(ID);
			 verificaID(_input.LT(-1).getText());
				                  _writeID = _input.LT(-1).getText();
				                  if (notUsed.contains(_writeID)) notUsed.remove(_writeID);
			                     
			setState(96);
			match(FP);
			setState(97);
			match(SC);

			               	  CommandEscrita cmd = new CommandEscrita(_writeID);
			               	  stack.peek().add(cmd);
			               
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CmdattribContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(IsiLangParser.ID, 0); }
		public TerminalNode ATTR() { return getToken(IsiLangParser.ATTR, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode SC() { return getToken(IsiLangParser.SC, 0); }
		public CmdattribContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdattrib; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterCmdattrib(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitCmdattrib(this);
		}
	}

	public final CmdattribContext cmdattrib() throws RecognitionException {
		CmdattribContext _localctx = new CmdattribContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_cmdattrib);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(100);
			match(ID);
			 verificaID(_input.LT(-1).getText());
			                    _exprID = _input.LT(-1).getText();
			                    if (notUsed.contains(_exprID)) notUsed.remove(_exprID);
			                    var = (IsiVariable)symbolTable.get(_exprID);
			                    _tipo = var.getType();
			                   
			setState(102);
			match(ATTR);
			 _exprContent = ""; 
			setState(104);
			expr();
			setState(105);
			match(SC);



			                 var.setValue(_exprContent);
			                 symbolTable.attributeValue(var.getName(), var);

			               	 CommandAtribuicao cmd = new CommandAtribuicao(_exprID, _exprContent);
			               	 stack.peek().add(cmd);
			               
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CmdescolhaContext extends ParserRuleContext {
		public TerminalNode AP() { return getToken(IsiLangParser.AP, 0); }
		public List<TerminalNode> ID() { return getTokens(IsiLangParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(IsiLangParser.ID, i);
		}
		public TerminalNode FP() { return getToken(IsiLangParser.FP, 0); }
		public TerminalNode ACH() { return getToken(IsiLangParser.ACH, 0); }
		public TerminalNode FCH() { return getToken(IsiLangParser.FCH, 0); }
		public List<TerminalNode> INTEGER() { return getTokens(IsiLangParser.INTEGER); }
		public TerminalNode INTEGER(int i) {
			return getToken(IsiLangParser.INTEGER, i);
		}
		public List<TerminalNode> DOUBLE() { return getTokens(IsiLangParser.DOUBLE); }
		public TerminalNode DOUBLE(int i) {
			return getToken(IsiLangParser.DOUBLE, i);
		}
		public List<TerminalNode> TEXT() { return getTokens(IsiLangParser.TEXT); }
		public TerminalNode TEXT(int i) {
			return getToken(IsiLangParser.TEXT, i);
		}
		public List<CmdContext> cmd() {
			return getRuleContexts(CmdContext.class);
		}
		public CmdContext cmd(int i) {
			return getRuleContext(CmdContext.class,i);
		}
		public CmdescolhaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdescolha; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterCmdescolha(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitCmdescolha(this);
		}
	}

	public final CmdescolhaContext cmdescolha() throws RecognitionException {
		CmdescolhaContext _localctx = new CmdescolhaContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_cmdescolha);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(108);
			match(T__9);
			setState(109);
			match(AP);
			setState(110);
			match(ID);

			                   verificaID(_input.LT(-1).getText());
			                   _decID = _input.LT(-1).getText();
			                   _exprSwitch = _input.LT(-1).getText();
			                   if (notUsed.contains(_decID)) notUsed.remove(_decID);
			                   casos = new ArrayList<ArrayList<AbstractCommand>>();
			                   condicao.clear();
			                   listaBloco = new ArrayList<AbstractCommand>();
			                   CommandEscolha cmd = new CommandEscolha(_exprSwitch, casos, condicao, listaBloco);
			                 
			setState(112);
			match(FP);
			setState(113);
			match(ACH);
			setState(128);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__10) {
				{
				{
				setState(114);
				match(T__10);
				setState(115);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ID) | (1L << INTEGER) | (1L << DOUBLE) | (1L << TEXT))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}

				                 _decID = _input.LT(-1).getText();
				                 condicao.add(_input.LT(-1).getText());
				                 cmd.setCondicao(condicao);
				                 if (notUsed.contains(_decID)) notUsed.remove(_decID);
				              
				setState(117);
				match(DP);

				                curThread = new ArrayList<AbstractCommand>();
				                stack.push(curThread);
				              
				setState(120); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(119);
					cmd();
					}
					}
					setState(122); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << T__12) | (1L << T__13) | (1L << ID))) != 0) );

				                casos.add(stack.pop());
				                cmd.setCasos(casos);
				              
				}
				}
				setState(130);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(141);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__11) {
				{
				setState(131);
				match(T__11);
				setState(132);
				match(DP);

				                curThread = new ArrayList<AbstractCommand>();
				                stack.push(curThread);
				              
				setState(135); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(134);
					cmd();
					}
					}
					setState(137); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << T__12) | (1L << T__13) | (1L << ID))) != 0) );

				                listaBloco = stack.pop();
				                cmd.setPadrao(listaBloco);
				              
				}
			}

			setState(143);
			match(FCH);


			                stack.peek().add(cmd);
			              
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CmdrepeticaoContext extends ParserRuleContext {
		public TerminalNode AP() { return getToken(IsiLangParser.AP, 0); }
		public List<TerminalNode> ID() { return getTokens(IsiLangParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(IsiLangParser.ID, i);
		}
		public TerminalNode OPREL() { return getToken(IsiLangParser.OPREL, 0); }
		public TerminalNode FP() { return getToken(IsiLangParser.FP, 0); }
		public TerminalNode ACH() { return getToken(IsiLangParser.ACH, 0); }
		public TerminalNode FCH() { return getToken(IsiLangParser.FCH, 0); }
		public TerminalNode INTEGER() { return getToken(IsiLangParser.INTEGER, 0); }
		public TerminalNode DOUBLE() { return getToken(IsiLangParser.DOUBLE, 0); }
		public List<CmdContext> cmd() {
			return getRuleContexts(CmdContext.class);
		}
		public CmdContext cmd(int i) {
			return getRuleContext(CmdContext.class,i);
		}
		public CmdrepeticaoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdrepeticao; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterCmdrepeticao(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitCmdrepeticao(this);
		}
	}

	public final CmdrepeticaoContext cmdrepeticao() throws RecognitionException {
		CmdrepeticaoContext _localctx = new CmdrepeticaoContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_cmdrepeticao);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(146);
			match(T__12);
			setState(147);
			match(AP);
			setState(148);
			match(ID);
			 verificaID(_input.LT(-1).getText());
			                                 _repID = _input.LT(-1).getText();
			                                 _exprRepetition = _input.LT(-1).getText();
			                                 if (notUsed.contains(_repID)) notUsed.remove(_repID);
			setState(150);
			match(OPREL);
			 _exprRepetition += _input.LT(-1).getText(); 
			setState(156);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				{
				setState(152);
				match(ID);

				                            verificaID(_input.LT(-1).getText());
				                         
				}
				break;
			case INTEGER:
				{
				setState(154);
				match(INTEGER);
				}
				break;
			case DOUBLE:
				{
				setState(155);
				match(DOUBLE);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}

			                            _repID = _input.LT(-1).getText();
			                            _exprRepetition += _input.LT(-1).getText();
			                            if (notUsed.contains(_repID)) notUsed.remove(_repID);
			                         
			setState(159);
			match(FP);
			setState(160);
			match(ACH);
			 curThread = new ArrayList<AbstractCommand>();
			                               stack.push(curThread);
			                         
			setState(163); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(162);
				cmd();
				}
				}
				setState(165); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << T__12) | (1L << T__13) | (1L << ID))) != 0) );
			setState(167);
			match(FCH);

			                            listaBloco = stack.pop();
			                            CommandRepeticao cmd = new CommandRepeticao(_exprRepetition, listaBloco);
			                            stack.peek().add(cmd);
			                          
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CmdselecaoContext extends ParserRuleContext {
		public TerminalNode AP() { return getToken(IsiLangParser.AP, 0); }
		public List<TerminalNode> ID() { return getTokens(IsiLangParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(IsiLangParser.ID, i);
		}
		public TerminalNode OPREL() { return getToken(IsiLangParser.OPREL, 0); }
		public TerminalNode FP() { return getToken(IsiLangParser.FP, 0); }
		public List<TerminalNode> ACH() { return getTokens(IsiLangParser.ACH); }
		public TerminalNode ACH(int i) {
			return getToken(IsiLangParser.ACH, i);
		}
		public List<TerminalNode> FCH() { return getTokens(IsiLangParser.FCH); }
		public TerminalNode FCH(int i) {
			return getToken(IsiLangParser.FCH, i);
		}
		public TerminalNode INTEGER() { return getToken(IsiLangParser.INTEGER, 0); }
		public TerminalNode DOUBLE() { return getToken(IsiLangParser.DOUBLE, 0); }
		public List<CmdContext> cmd() {
			return getRuleContexts(CmdContext.class);
		}
		public CmdContext cmd(int i) {
			return getRuleContext(CmdContext.class,i);
		}
		public CmdselecaoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdselecao; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterCmdselecao(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitCmdselecao(this);
		}
	}

	public final CmdselecaoContext cmdselecao() throws RecognitionException {
		CmdselecaoContext _localctx = new CmdselecaoContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_cmdselecao);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(170);
			match(T__13);
			setState(171);
			match(AP);
			setState(172);
			match(ID);

			                            verificaID(_input.LT(-1).getText());
			                            _decID = _input.LT(-1).getText();
			                            _exprDecision = _input.LT(-1).getText();
			                            if (notUsed.contains(_decID)) notUsed.remove(_decID);
			                            
			setState(174);
			match(OPREL);
			 _exprDecision += _input.LT(-1).getText(); 
			setState(180);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				{
				setState(176);
				match(ID);

				                        verificaID(_input.LT(-1).getText());
				                    
				}
				break;
			case INTEGER:
				{
				setState(178);
				match(INTEGER);
				}
				break;
			case DOUBLE:
				{
				setState(179);
				match(DOUBLE);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}

			                        _decID = _input.LT(-1).getText();
			                        _exprDecision += _input.LT(-1).getText();
			                        if (notUsed.contains(_decID)) notUsed.remove(_decID);
			                    
			setState(183);
			match(FP);
			setState(184);
			match(T__14);
			setState(185);
			match(ACH);
			 curThread = new ArrayList<AbstractCommand>(); 
			                      stack.push(curThread);
			                    
			setState(188); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(187);
				cmd();
				}
				}
				setState(190); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << T__12) | (1L << T__13) | (1L << ID))) != 0) );
			setState(192);
			match(FCH);

			                       listaTrue = stack.pop();
			                       CommandDecisao cmd = new CommandDecisao(_exprDecision, listaTrue, new ArrayList<AbstractCommand>());
			                    
			setState(205);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__15) {
				{
				setState(194);
				match(T__15);
				setState(195);
				match(ACH);

				                   	 	curThread = new ArrayList<AbstractCommand>();
				                   	 	stack.push(curThread);
				                   	 
				{
				setState(198); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(197);
					cmd();
					}
					}
					setState(200); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << T__12) | (1L << T__13) | (1L << ID))) != 0) );
				}
				setState(202);
				match(FCH);

				                   		listaFalse = stack.pop();
				                   		cmd.setListaFalse(listaFalse);

				                   	
				}
			}


			                     stack.peek().add(cmd);
			                   
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExprContext extends ParserRuleContext {
		public ExprmatContext exprmat() {
			return getRuleContext(ExprmatContext.class,0);
		}
		public ExprtextContext exprtext() {
			return getRuleContext(ExprtextContext.class,0);
		}
		public ExprboolContext exprbool() {
			return getRuleContext(ExprboolContext.class,0);
		}
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitExpr(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		ExprContext _localctx = new ExprContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_expr);
		try {
			setState(214);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(209);
				exprmat();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(210);
				exprtext();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(211);
				exprbool();

				                if (_tipo != IsiVariable.BOOL) throw new IsiSemanticException("Data type error in variable "
				                + var.getName() + " : expected a " + textoTipo(_tipo) + " but got a BOOL.");
				            
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExprmatContext extends ParserRuleContext {
		public TermoContext termo() {
			return getRuleContext(TermoContext.class,0);
		}
		public List<TerminalNode> OP() { return getTokens(IsiLangParser.OP); }
		public TerminalNode OP(int i) {
			return getToken(IsiLangParser.OP, i);
		}
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public List<TerminalNode> AP() { return getTokens(IsiLangParser.AP); }
		public TerminalNode AP(int i) {
			return getToken(IsiLangParser.AP, i);
		}
		public List<TerminalNode> FP() { return getTokens(IsiLangParser.FP); }
		public TerminalNode FP(int i) {
			return getToken(IsiLangParser.FP, i);
		}
		public ExprmatContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exprmat; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterExprmat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitExprmat(this);
		}
	}

	public final ExprmatContext exprmat() throws RecognitionException {
		ExprmatContext _localctx = new ExprmatContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_exprmat);
		try {
			int _alt;
			setState(255);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
			case INTEGER:
			case DOUBLE:
				enterOuterAlt(_localctx, 1);
				{
				setState(216);
				termo();
				setState(222);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(217);
						match(OP);
						 _exprContent += _input.LT(-1).getText();
						setState(219);
						expr();
						}
						} 
					}
					setState(224);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
				}
				}
				break;
			case AP:
				enterOuterAlt(_localctx, 2);
				{
				setState(225);
				match(AP);
				 _exprContent += _input.LT(-1).getText();
				setState(233);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,17,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(227);
						expr();
						setState(228);
						match(AP);
						 _exprContent += _input.LT(-1).getText();
						}
						} 
					}
					setState(235);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,17,_ctx);
				}
				setState(236);
				expr();
				setState(242);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,18,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(237);
						match(FP);
						 _exprContent += _input.LT(-1).getText();
						setState(239);
						expr();
						}
						} 
					}
					setState(244);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,18,_ctx);
				}
				setState(245);
				match(FP);
				 _exprContent += _input.LT(-1).getText();
				setState(252);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,19,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(247);
						match(OP);
						 _exprContent += _input.LT(-1).getText();
						setState(249);
						expr();
						}
						} 
					}
					setState(254);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,19,_ctx);
				}
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExprtextContext extends ParserRuleContext {
		public TerminalNode TEXT() { return getToken(IsiLangParser.TEXT, 0); }
		public TerminalNode CHAR() { return getToken(IsiLangParser.CHAR, 0); }
		public ExprtextContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exprtext; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterExprtext(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitExprtext(this);
		}
	}

	public final ExprtextContext exprtext() throws RecognitionException {
		ExprtextContext _localctx = new ExprtextContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_exprtext);
		try {
			setState(261);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case TEXT:
				enterOuterAlt(_localctx, 1);
				{
				setState(257);
				match(TEXT);

				                  if (_tipo != IsiVariable.TEXT) throw new IsiSemanticException("Data type error in variable "
				                    + var.getName() + " : expected a " + textoTipo(_tipo) + " but got a TEXT.");
				                  _exprContent += _input.LT(-1).getText();
				                
				}
				break;
			case CHAR:
				enterOuterAlt(_localctx, 2);
				{
				setState(259);
				match(CHAR);

				                  if (_tipo != IsiVariable.TEXT && _tipo != IsiVariable.CHAR)
				                  throw new IsiSemanticException("Data type error in variable "
				                    + var.getName() + " : expected a " + textoTipo(_tipo) + " but got a CHAR.");
				                  _exprContent += _input.LT(-1).getText();
				                
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExprboolContext extends ParserRuleContext {
		public TerminalNode BOOL() { return getToken(IsiLangParser.BOOL, 0); }
		public List<TerminalNode> ID() { return getTokens(IsiLangParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(IsiLangParser.ID, i);
		}
		public TerminalNode OPREL() { return getToken(IsiLangParser.OPREL, 0); }
		public TerminalNode INTEGER() { return getToken(IsiLangParser.INTEGER, 0); }
		public TerminalNode DOUBLE() { return getToken(IsiLangParser.DOUBLE, 0); }
		public ExprboolContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exprbool; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterExprbool(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitExprbool(this);
		}
	}

	public final ExprboolContext exprbool() throws RecognitionException {
		ExprboolContext _localctx = new ExprboolContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_exprbool);
		try {
			setState(276);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case BOOL:
				enterOuterAlt(_localctx, 1);
				{
				setState(263);
				match(BOOL);
				 _exprContent += _input.LT(-1).getText(); 
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 2);
				{
				setState(265);
				match(ID);

				                    verificaID(_input.LT(-1).getText());
				                    _decID = _input.LT(-1).getText();
				                    _exprContent = _input.LT(-1).getText();
				                    if (notUsed.contains(_decID)) notUsed.remove(_decID);
				                
				setState(267);
				match(OPREL);
				 _exprContent += _input.LT(-1).getText(); 
				setState(273);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case ID:
					{
					setState(269);
					match(ID);

					                  verificaID(_input.LT(-1).getText());
					                
					}
					break;
				case INTEGER:
					{
					setState(271);
					match(INTEGER);
					}
					break;
				case DOUBLE:
					{
					setState(272);
					match(DOUBLE);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}

				                  _decID = _input.LT(-1).getText();
				                  _exprContent += _input.LT(-1).getText();
				                  if (notUsed.contains(_decID)) notUsed.remove(_decID);
				                
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TermoContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(IsiLangParser.ID, 0); }
		public TerminalNode INTEGER() { return getToken(IsiLangParser.INTEGER, 0); }
		public TerminalNode DOUBLE() { return getToken(IsiLangParser.DOUBLE, 0); }
		public TermoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_termo; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterTermo(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitTermo(this);
		}
	}

	public final TermoContext termo() throws RecognitionException {
		TermoContext _localctx = new TermoContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_termo);
		try {
			setState(284);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(278);
				match(ID);
				 verificaID(_input.LT(-1).getText());
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
				break;
			case INTEGER:
				enterOuterAlt(_localctx, 2);
				{
				setState(280);
				match(INTEGER);

				                if(_tipo != IsiVariable.INTEGER && _tipo != IsiVariable.DOUBLE && _tipo != IsiVariable.CHAR)
				                throw new IsiSemanticException("Data type error in variable "
				                    + var.getName() + " : expected a " + textoTipo(_tipo) + " but got a INTEGER.");
				              	_exprContent += _input.LT(-1).getText();
				              
				}
				break;
			case DOUBLE:
				enterOuterAlt(_localctx, 3);
				{
				setState(282);
				match(DOUBLE);

				                if(_tipo != IsiVariable.DOUBLE) throw new IsiSemanticException("Data type error in variable "
				                  + var.getName() + " : expected a " + textoTipo(_tipo) + " but got a INTEGER.");;
				                _exprContent += _input.LT(-1).getText();
				              
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3%\u0121\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\3\2\3\2\3\2\3\2\3\2\3\2\3\3\6\3,\n\3\r\3\16\3-\3\4\3\4\3\4\3\4\3\4\3"+
		"\4\7\4\66\n\4\f\4\16\49\13\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3"+
		"\5\3\5\5\5G\n\5\3\6\3\6\6\6K\n\6\r\6\16\6L\3\7\3\7\3\7\3\7\3\7\3\7\5\7"+
		"U\n\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t"+
		"\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3"+
		"\13\3\13\3\13\3\13\3\13\6\13{\n\13\r\13\16\13|\3\13\3\13\7\13\u0081\n"+
		"\13\f\13\16\13\u0084\13\13\3\13\3\13\3\13\3\13\6\13\u008a\n\13\r\13\16"+
		"\13\u008b\3\13\3\13\5\13\u0090\n\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f"+
		"\3\f\3\f\3\f\3\f\3\f\5\f\u009f\n\f\3\f\3\f\3\f\3\f\3\f\6\f\u00a6\n\f\r"+
		"\f\16\f\u00a7\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\5\r"+
		"\u00b7\n\r\3\r\3\r\3\r\3\r\3\r\3\r\6\r\u00bf\n\r\r\r\16\r\u00c0\3\r\3"+
		"\r\3\r\3\r\3\r\3\r\6\r\u00c9\n\r\r\r\16\r\u00ca\3\r\3\r\3\r\5\r\u00d0"+
		"\n\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\5\16\u00d9\n\16\3\17\3\17\3\17\3"+
		"\17\7\17\u00df\n\17\f\17\16\17\u00e2\13\17\3\17\3\17\3\17\3\17\3\17\3"+
		"\17\7\17\u00ea\n\17\f\17\16\17\u00ed\13\17\3\17\3\17\3\17\3\17\7\17\u00f3"+
		"\n\17\f\17\16\17\u00f6\13\17\3\17\3\17\3\17\3\17\3\17\7\17\u00fd\n\17"+
		"\f\17\16\17\u0100\13\17\5\17\u0102\n\17\3\20\3\20\3\20\3\20\5\20\u0108"+
		"\n\20\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\5\21\u0114\n\21"+
		"\3\21\5\21\u0117\n\21\3\22\3\22\3\22\3\22\3\22\3\22\5\22\u011f\n\22\3"+
		"\22\2\2\23\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"\2\3\3\2 #\2\u0134"+
		"\2$\3\2\2\2\4+\3\2\2\2\6/\3\2\2\2\bF\3\2\2\2\nH\3\2\2\2\fT\3\2\2\2\16"+
		"V\3\2\2\2\20^\3\2\2\2\22f\3\2\2\2\24n\3\2\2\2\26\u0094\3\2\2\2\30\u00ac"+
		"\3\2\2\2\32\u00d8\3\2\2\2\34\u0101\3\2\2\2\36\u0107\3\2\2\2 \u0116\3\2"+
		"\2\2\"\u011e\3\2\2\2$%\7\3\2\2%&\5\4\3\2&\'\5\n\6\2\'(\7\4\2\2()\b\2\1"+
		"\2)\3\3\2\2\2*,\5\6\4\2+*\3\2\2\2,-\3\2\2\2-+\3\2\2\2-.\3\2\2\2.\5\3\2"+
		"\2\2/\60\5\b\5\2\60\61\7 \2\2\61\67\b\4\1\2\62\63\7\32\2\2\63\64\7 \2"+
		"\2\64\66\b\4\1\2\65\62\3\2\2\2\669\3\2\2\2\67\65\3\2\2\2\678\3\2\2\28"+
		":\3\2\2\29\67\3\2\2\2:;\7\27\2\2;\7\3\2\2\2<=\7\5\2\2=G\b\5\1\2>?\7\6"+
		"\2\2?G\b\5\1\2@A\7\7\2\2AG\b\5\1\2BC\7\b\2\2CG\b\5\1\2DE\7\t\2\2EG\b\5"+
		"\1\2F<\3\2\2\2F>\3\2\2\2F@\3\2\2\2FB\3\2\2\2FD\3\2\2\2G\t\3\2\2\2HJ\b"+
		"\6\1\2IK\5\f\7\2JI\3\2\2\2KL\3\2\2\2LJ\3\2\2\2LM\3\2\2\2M\13\3\2\2\2N"+
		"U\5\16\b\2OU\5\20\t\2PU\5\22\n\2QU\5\30\r\2RU\5\26\f\2SU\5\24\13\2TN\3"+
		"\2\2\2TO\3\2\2\2TP\3\2\2\2TQ\3\2\2\2TR\3\2\2\2TS\3\2\2\2U\r\3\2\2\2VW"+
		"\7\n\2\2WX\7\23\2\2XY\7 \2\2YZ\b\b\1\2Z[\7\24\2\2[\\\7\27\2\2\\]\b\b\1"+
		"\2]\17\3\2\2\2^_\7\13\2\2_`\7\23\2\2`a\7 \2\2ab\b\t\1\2bc\7\24\2\2cd\7"+
		"\27\2\2de\b\t\1\2e\21\3\2\2\2fg\7 \2\2gh\b\n\1\2hi\7\31\2\2ij\b\n\1\2"+
		"jk\5\32\16\2kl\7\27\2\2lm\b\n\1\2m\23\3\2\2\2no\7\f\2\2op\7\23\2\2pq\7"+
		" \2\2qr\b\13\1\2rs\7\24\2\2s\u0082\7\33\2\2tu\7\r\2\2uv\t\2\2\2vw\b\13"+
		"\1\2wx\7\26\2\2xz\b\13\1\2y{\5\f\7\2zy\3\2\2\2{|\3\2\2\2|z\3\2\2\2|}\3"+
		"\2\2\2}~\3\2\2\2~\177\b\13\1\2\177\u0081\3\2\2\2\u0080t\3\2\2\2\u0081"+
		"\u0084\3\2\2\2\u0082\u0080\3\2\2\2\u0082\u0083\3\2\2\2\u0083\u008f\3\2"+
		"\2\2\u0084\u0082\3\2\2\2\u0085\u0086\7\16\2\2\u0086\u0087\7\26\2\2\u0087"+
		"\u0089\b\13\1\2\u0088\u008a\5\f\7\2\u0089\u0088\3\2\2\2\u008a\u008b\3"+
		"\2\2\2\u008b\u0089\3\2\2\2\u008b\u008c\3\2\2\2\u008c\u008d\3\2\2\2\u008d"+
		"\u008e\b\13\1\2\u008e\u0090\3\2\2\2\u008f\u0085\3\2\2\2\u008f\u0090\3"+
		"\2\2\2\u0090\u0091\3\2\2\2\u0091\u0092\7\34\2\2\u0092\u0093\b\13\1\2\u0093"+
		"\25\3\2\2\2\u0094\u0095\7\17\2\2\u0095\u0096\7\23\2\2\u0096\u0097\7 \2"+
		"\2\u0097\u0098\b\f\1\2\u0098\u0099\7\36\2\2\u0099\u009e\b\f\1\2\u009a"+
		"\u009b\7 \2\2\u009b\u009f\b\f\1\2\u009c\u009f\7!\2\2\u009d\u009f\7\"\2"+
		"\2\u009e\u009a\3\2\2\2\u009e\u009c\3\2\2\2\u009e\u009d\3\2\2\2\u009f\u00a0"+
		"\3\2\2\2\u00a0\u00a1\b\f\1\2\u00a1\u00a2\7\24\2\2\u00a2\u00a3\7\33\2\2"+
		"\u00a3\u00a5\b\f\1\2\u00a4\u00a6\5\f\7\2\u00a5\u00a4\3\2\2\2\u00a6\u00a7"+
		"\3\2\2\2\u00a7\u00a5\3\2\2\2\u00a7\u00a8\3\2\2\2\u00a8\u00a9\3\2\2\2\u00a9"+
		"\u00aa\7\34\2\2\u00aa\u00ab\b\f\1\2\u00ab\27\3\2\2\2\u00ac\u00ad\7\20"+
		"\2\2\u00ad\u00ae\7\23\2\2\u00ae\u00af\7 \2\2\u00af\u00b0\b\r\1\2\u00b0"+
		"\u00b1\7\36\2\2\u00b1\u00b6\b\r\1\2\u00b2\u00b3\7 \2\2\u00b3\u00b7\b\r"+
		"\1\2\u00b4\u00b7\7!\2\2\u00b5\u00b7\7\"\2\2\u00b6\u00b2\3\2\2\2\u00b6"+
		"\u00b4\3\2\2\2\u00b6\u00b5\3\2\2\2\u00b7\u00b8\3\2\2\2\u00b8\u00b9\b\r"+
		"\1\2\u00b9\u00ba\7\24\2\2\u00ba\u00bb\7\21\2\2\u00bb\u00bc\7\33\2\2\u00bc"+
		"\u00be\b\r\1\2\u00bd\u00bf\5\f\7\2\u00be\u00bd\3\2\2\2\u00bf\u00c0\3\2"+
		"\2\2\u00c0\u00be\3\2\2\2\u00c0\u00c1\3\2\2\2\u00c1\u00c2\3\2\2\2\u00c2"+
		"\u00c3\7\34\2\2\u00c3\u00cf\b\r\1\2\u00c4\u00c5\7\22\2\2\u00c5\u00c6\7"+
		"\33\2\2\u00c6\u00c8\b\r\1\2\u00c7\u00c9\5\f\7\2\u00c8\u00c7\3\2\2\2\u00c9"+
		"\u00ca\3\2\2\2\u00ca\u00c8\3\2\2\2\u00ca\u00cb\3\2\2\2\u00cb\u00cc\3\2"+
		"\2\2\u00cc\u00cd\7\34\2\2\u00cd\u00ce\b\r\1\2\u00ce\u00d0\3\2\2\2\u00cf"+
		"\u00c4\3\2\2\2\u00cf\u00d0\3\2\2\2\u00d0\u00d1\3\2\2\2\u00d1\u00d2\b\r"+
		"\1\2\u00d2\31\3\2\2\2\u00d3\u00d9\5\34\17\2\u00d4\u00d9\5\36\20\2\u00d5"+
		"\u00d6\5 \21\2\u00d6\u00d7\b\16\1\2\u00d7\u00d9\3\2\2\2\u00d8\u00d3\3"+
		"\2\2\2\u00d8\u00d4\3\2\2\2\u00d8\u00d5\3\2\2\2\u00d9\33\3\2\2\2\u00da"+
		"\u00e0\5\"\22\2\u00db\u00dc\7\30\2\2\u00dc\u00dd\b\17\1\2\u00dd\u00df"+
		"\5\32\16\2\u00de\u00db\3\2\2\2\u00df\u00e2\3\2\2\2\u00e0\u00de\3\2\2\2"+
		"\u00e0\u00e1\3\2\2\2\u00e1\u0102\3\2\2\2\u00e2\u00e0\3\2\2\2\u00e3\u00e4"+
		"\7\23\2\2\u00e4\u00eb\b\17\1\2\u00e5\u00e6\5\32\16\2\u00e6\u00e7\7\23"+
		"\2\2\u00e7\u00e8\b\17\1\2\u00e8\u00ea\3\2\2\2\u00e9\u00e5\3\2\2\2\u00ea"+
		"\u00ed\3\2\2\2\u00eb\u00e9\3\2\2\2\u00eb\u00ec\3\2\2\2\u00ec\u00ee\3\2"+
		"\2\2\u00ed\u00eb\3\2\2\2\u00ee\u00f4\5\32\16\2\u00ef\u00f0\7\24\2\2\u00f0"+
		"\u00f1\b\17\1\2\u00f1\u00f3\5\32\16\2\u00f2\u00ef\3\2\2\2\u00f3\u00f6"+
		"\3\2\2\2\u00f4\u00f2\3\2\2\2\u00f4\u00f5\3\2\2\2\u00f5\u00f7\3\2\2\2\u00f6"+
		"\u00f4\3\2\2\2\u00f7\u00f8\7\24\2\2\u00f8\u00fe\b\17\1\2\u00f9\u00fa\7"+
		"\30\2\2\u00fa\u00fb\b\17\1\2\u00fb\u00fd\5\32\16\2\u00fc\u00f9\3\2\2\2"+
		"\u00fd\u0100\3\2\2\2\u00fe\u00fc\3\2\2\2\u00fe\u00ff\3\2\2\2\u00ff\u0102"+
		"\3\2\2\2\u0100\u00fe\3\2\2\2\u0101\u00da\3\2\2\2\u0101\u00e3\3\2\2\2\u0102"+
		"\35\3\2\2\2\u0103\u0104\7#\2\2\u0104\u0108\b\20\1\2\u0105\u0106\7$\2\2"+
		"\u0106\u0108\b\20\1\2\u0107\u0103\3\2\2\2\u0107\u0105\3\2\2\2\u0108\37"+
		"\3\2\2\2\u0109\u010a\7\37\2\2\u010a\u0117\b\21\1\2\u010b\u010c\7 \2\2"+
		"\u010c\u010d\b\21\1\2\u010d\u010e\7\36\2\2\u010e\u0113\b\21\1\2\u010f"+
		"\u0110\7 \2\2\u0110\u0114\b\21\1\2\u0111\u0114\7!\2\2\u0112\u0114\7\""+
		"\2\2\u0113\u010f\3\2\2\2\u0113\u0111\3\2\2\2\u0113\u0112\3\2\2\2\u0114"+
		"\u0115\3\2\2\2\u0115\u0117\b\21\1\2\u0116\u0109\3\2\2\2\u0116\u010b\3"+
		"\2\2\2\u0117!\3\2\2\2\u0118\u0119\7 \2\2\u0119\u011f\b\22\1\2\u011a\u011b"+
		"\7!\2\2\u011b\u011f\b\22\1\2\u011c\u011d\7\"\2\2\u011d\u011f\b\22\1\2"+
		"\u011e\u0118\3\2\2\2\u011e\u011a\3\2\2\2\u011e\u011c\3\2\2\2\u011f#\3"+
		"\2\2\2\33-\67FLT|\u0082\u008b\u008f\u009e\u00a7\u00b6\u00c0\u00ca\u00cf"+
		"\u00d8\u00e0\u00eb\u00f4\u00fe\u0101\u0107\u0113\u0116\u011e";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}