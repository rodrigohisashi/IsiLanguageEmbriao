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
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, AP=15, FP=16, ASP=17, 
		DP=18, SC=19, OP=20, ATTR=21, VIR=22, ACH=23, FCH=24, OPLOG=25, OPREL=26, 
		ID=27, INTEGER=28, DOUBLE=29, TEXT=30, SP=31, AC=32, WS=33;
	public static final int
		RULE_prog = 0, RULE_decl = 1, RULE_declaravar = 2, RULE_tipo = 3, RULE_bloco = 4, 
		RULE_cmd = 5, RULE_cmdleitura = 6, RULE_cmdescrita = 7, RULE_cmdattrib = 8, 
		RULE_cmdescolha = 9, RULE_cmdrepeticao = 10, RULE_cmdselecao = 11, RULE_expr = 12, 
		RULE_termo = 13;
	public static final String[] ruleNames = {
		"prog", "decl", "declaravar", "tipo", "bloco", "cmd", "cmdleitura", "cmdescrita", 
		"cmdattrib", "cmdescolha", "cmdrepeticao", "cmdselecao", "expr", "termo"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'programa'", "'fimprog;'", "'inteiro'", "'texto'", "'real'", "'leia'", 
		"'escreva'", "'escolha'", "'caso'", "'padrao'", "'enquanto'", "'se'", 
		"'entao'", "'senao'", "'('", "')'", "'\"'", "':'", "'.'", null, "':='", 
		"','", "'{'", "'}'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, "AP", "FP", "ASP", "DP", "SC", "OP", "ATTR", "VIR", 
		"ACH", "FCH", "OPLOG", "OPREL", "ID", "INTEGER", "DOUBLE", "TEXT", "SP", 
		"AC", "WS"
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
		
		public void exibeComandos(){
			for (AbstractCommand c: program.getComandos()){
				System.out.println(c);
			}
		}
		
		public void generateCode(){
			program.generateTarget();
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
			setState(28);
			match(T__0);
			setState(29);
			decl();
			setState(30);
			bloco();
			setState(31);
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
			setState(35); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(34);
				declaravar();
				}
				}
				setState(37); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__3) | (1L << T__4))) != 0) );
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
			setState(39);
			tipo();
			setState(40);
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
			                    
			setState(47);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==VIR) {
				{
				{
				setState(42);
				match(VIR);
				setState(43);
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
				setState(49);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(50);
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
			setState(58);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__2:
				enterOuterAlt(_localctx, 1);
				{
				setState(52);
				match(T__2);
				 _tipo = IsiVariable.INTEGER;  
				}
				break;
			case T__3:
				enterOuterAlt(_localctx, 2);
				{
				setState(54);
				match(T__3);
				 _tipo = IsiVariable.TEXT;  
				}
				break;
			case T__4:
				enterOuterAlt(_localctx, 3);
				{
				setState(56);
				match(T__4);
				 _tipo = IsiVariable.DOUBLE;  
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
			          
			setState(62); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(61);
				cmd();
				}
				}
				setState(64); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__10) | (1L << T__11) | (1L << ID))) != 0) );
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
			setState(72);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__5:
				enterOuterAlt(_localctx, 1);
				{
				setState(66);
				cmdleitura();
				}
				break;
			case T__6:
				enterOuterAlt(_localctx, 2);
				{
				setState(67);
				cmdescrita();
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 3);
				{
				setState(68);
				cmdattrib();
				}
				break;
			case T__11:
				enterOuterAlt(_localctx, 4);
				{
				setState(69);
				cmdselecao();
				}
				break;
			case T__10:
				enterOuterAlt(_localctx, 5);
				{
				setState(70);
				cmdrepeticao();
				}
				break;
			case T__7:
				enterOuterAlt(_localctx, 6);
				{
				setState(71);
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
			setState(74);
			match(T__5);
			setState(75);
			match(AP);
			setState(76);
			match(ID);
			 verificaID(_input.LT(-1).getText());
			                     	  _readID = _input.LT(-1).getText();
			                     	  if (notUsed.contains(_readID)) notUsed.remove(_readID);
			                        
			setState(78);
			match(FP);
			setState(79);
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
			setState(82);
			match(T__6);
			setState(83);
			match(AP);
			setState(84);
			match(ID);
			 verificaID(_input.LT(-1).getText());
				                  _writeID = _input.LT(-1).getText();
				                  if (notUsed.contains(_writeID)) notUsed.remove(_writeID);
			                     
			setState(86);
			match(FP);
			setState(87);
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
			setState(90);
			match(ID);
			 verificaID(_input.LT(-1).getText());
			                    _exprID = _input.LT(-1).getText();
			                    if (notUsed.contains(_exprID)) notUsed.remove(_exprID);
			                    var = (IsiVariable)symbolTable.get(_exprID);
			                    _tipo = var.getType();
			                   
			setState(92);
			match(ATTR);
			 _exprContent = ""; 
			setState(94);
			expr();
			setState(95);
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
			setState(98);
			match(T__7);
			setState(99);
			match(AP);
			setState(100);
			match(ID);

			                   verificaID(_input.LT(-1).getText());
			                   _decID = _input.LT(-1).getText();
			                   _exprSwitch = _input.LT(-1).getText();
			                   if (notUsed.contains(_decID)) notUsed.remove(_decID);
			                   casos = new ArrayList<ArrayList<AbstractCommand>>();
			                   condicao.clear();
			                   listaBloco = new ArrayList<AbstractCommand>();
			                   CommandEscolha cmd = new CommandEscolha(_exprSwitch, casos, condicao, listaBloco);
			                 
			setState(102);
			match(FP);
			setState(103);
			match(ACH);
			setState(118);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__8) {
				{
				{
				setState(104);
				match(T__8);
				setState(105);
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
				              
				setState(107);
				match(DP);

				                curThread = new ArrayList<AbstractCommand>();
				                stack.push(curThread);
				              
				setState(110); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(109);
					cmd();
					}
					}
					setState(112); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__10) | (1L << T__11) | (1L << ID))) != 0) );

				                casos.add(stack.pop());
				                cmd.setCasos(casos);
				              
				}
				}
				setState(120);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(131);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__9) {
				{
				setState(121);
				match(T__9);
				setState(122);
				match(DP);

				                curThread = new ArrayList<AbstractCommand>();
				                stack.push(curThread);
				              
				setState(125); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(124);
					cmd();
					}
					}
					setState(127); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__10) | (1L << T__11) | (1L << ID))) != 0) );

				                listaBloco = stack.pop();
				                cmd.setPadrao(listaBloco);
				              
				}
			}

			setState(133);
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
			setState(136);
			match(T__10);
			setState(137);
			match(AP);
			setState(138);
			match(ID);
			 _repID = _input.LT(-1).getText();
			                                 _exprRepetition = _input.LT(-1).getText();
			                                 if (notUsed.contains(_repID)) notUsed.remove(_repID);
			setState(140);
			match(OPREL);
			 _exprRepetition += _input.LT(-1).getText(); 
			setState(142);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ID) | (1L << INTEGER) | (1L << DOUBLE))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}

			                                        _repID = _input.LT(-1).getText();
			                                        _exprRepetition += _input.LT(-1).getText();
			                                        if (notUsed.contains(_repID)) notUsed.remove(_repID);
			                                        
			setState(144);
			match(FP);
			setState(145);
			match(ACH);
			 curThread = new ArrayList<AbstractCommand>();
			                               stack.push(curThread);
			                         
			setState(148); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(147);
				cmd();
				}
				}
				setState(150); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__10) | (1L << T__11) | (1L << ID))) != 0) );
			setState(152);
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
			setState(155);
			match(T__11);
			setState(156);
			match(AP);
			setState(157);
			match(ID);

			                            _decID = _input.LT(-1).getText();
			                            _exprDecision = _input.LT(-1).getText();
			                            if (notUsed.contains(_decID)) notUsed.remove(_decID);
			                            
			setState(159);
			match(OPREL);
			 _exprDecision += _input.LT(-1).getText(); 
			setState(161);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ID) | (1L << INTEGER) | (1L << DOUBLE))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}

			                            _decID = _input.LT(-1).getText();
			                            _exprDecision += _input.LT(-1).getText();
			                            if (notUsed.contains(_decID)) notUsed.remove(_decID);
			                            
			setState(163);
			match(FP);
			setState(164);
			match(T__12);
			setState(165);
			match(ACH);
			 curThread = new ArrayList<AbstractCommand>(); 
			                      stack.push(curThread);
			                    
			setState(168); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(167);
				cmd();
				}
				}
				setState(170); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__10) | (1L << T__11) | (1L << ID))) != 0) );
			setState(172);
			match(FCH);

			                       listaTrue = stack.pop();
			                       CommandDecisao cmd = new CommandDecisao(_exprDecision, listaTrue, new ArrayList<AbstractCommand>());
			                    
			setState(185);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__13) {
				{
				setState(174);
				match(T__13);
				setState(175);
				match(ACH);

				                   	 	curThread = new ArrayList<AbstractCommand>();
				                   	 	stack.push(curThread);
				                   	 
				{
				setState(178); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(177);
					cmd();
					}
					}
					setState(180); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__10) | (1L << T__11) | (1L << ID))) != 0) );
				}
				setState(182);
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
			int _alt;
			setState(228);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
			case INTEGER:
			case DOUBLE:
			case TEXT:
				enterOuterAlt(_localctx, 1);
				{
				setState(189);
				termo();
				setState(195);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,13,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(190);
						match(OP);
						 _exprContent += _input.LT(-1).getText();
						setState(192);
						expr();
						}
						} 
					}
					setState(197);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,13,_ctx);
				}
				}
				break;
			case AP:
				enterOuterAlt(_localctx, 2);
				{
				setState(198);
				match(AP);
				 _exprContent += _input.LT(-1).getText();
				setState(206);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(200);
						expr();
						setState(201);
						match(AP);
						 _exprContent += _input.LT(-1).getText();
						}
						} 
					}
					setState(208);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
				}
				setState(209);
				expr();
				setState(215);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,15,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(210);
						match(FP);
						 _exprContent += _input.LT(-1).getText();
						setState(212);
						expr();
						}
						} 
					}
					setState(217);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,15,_ctx);
				}
				setState(218);
				match(FP);
				 _exprContent += _input.LT(-1).getText();
				setState(225);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(220);
						match(OP);
						 _exprContent += _input.LT(-1).getText();
						setState(222);
						expr();
						}
						} 
					}
					setState(227);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
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

	public static class TermoContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(IsiLangParser.ID, 0); }
		public TerminalNode INTEGER() { return getToken(IsiLangParser.INTEGER, 0); }
		public TerminalNode DOUBLE() { return getToken(IsiLangParser.DOUBLE, 0); }
		public TerminalNode TEXT() { return getToken(IsiLangParser.TEXT, 0); }
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
		enterRule(_localctx, 26, RULE_termo);
		try {
			setState(238);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(230);
				match(ID);
				 verificaID(_input.LT(-1).getText());
				                   _varName = _input.LT(-1).getText();
					               IsiVariable varAttr = (IsiVariable)symbolTable.get(_input.LT(-1).getText());
					               if (_tipo == IsiVariable.INTEGER && _tipo != varAttr.getType()) {
					                    throw new IsiSemanticException("Data type error in variable " + var.getName() + " : expected a INTEGER but got a ");
					               }
					               if (_tipo == IsiVariable.TEXT && _tipo != varAttr.getType()) {
					                    throw new IsiSemanticException("Data type error in variable " + var.getName() + " : expected a TEXT but got a " );
					               }
					               if (_tipo == IsiVariable.DOUBLE && _tipo != varAttr.getType() && varAttr.getType() == IsiVariable.TEXT) {
					                    throw new IsiSemanticException("Data type error in variable " + var.getName() + " : expected a DOUBLE but got a TEXT");
					               }
				                   if (notUsed.contains(_input.LT(-1).getText())) notUsed.remove(_input.LT(-1).getText());
				                   _exprContent += _input.LT(-1).getText();
				                 
				}
				break;
			case INTEGER:
				enterOuterAlt(_localctx, 2);
				{
				setState(232);
				match(INTEGER);

				                if(_tipo == 1) throw new IsiSemanticException("Data type error in variable " + var.getName() + " : expected a string but got a number.");
				              	_exprContent += _input.LT(-1).getText();
				              
				}
				break;
			case DOUBLE:
				enterOuterAlt(_localctx, 3);
				{
				setState(234);
				match(DOUBLE);

				                if(_tipo == 1) throw new IsiSemanticException("Data type error in variable " + var.getName() + " : expected a string but got a number.");
				                if(_tipo == 0) throw new IsiSemanticException("Data type error in variable " + var.getName() + " : expected a INTEGER but got a DOUBLE.");
				                _exprContent += _input.LT(-1).getText();
				              
				}
				break;
			case TEXT:
				enterOuterAlt(_localctx, 4);
				{
				setState(236);
				match(TEXT);

				                if (_tipo == 0) throw new IsiSemanticException("Data type error in variable " + var.getName() + " : expected a number but got a string.");
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3#\u00f3\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\3\2\3\2\3\2\3\2\3\2\3\2\3\3\6"+
		"\3&\n\3\r\3\16\3\'\3\4\3\4\3\4\3\4\3\4\3\4\7\4\60\n\4\f\4\16\4\63\13\4"+
		"\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\5\5=\n\5\3\6\3\6\6\6A\n\6\r\6\16\6B\3"+
		"\7\3\7\3\7\3\7\3\7\3\7\5\7K\n\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3"+
		"\t\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3"+
		"\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\6\13q\n\13\r\13\16\13"+
		"r\3\13\3\13\7\13w\n\13\f\13\16\13z\13\13\3\13\3\13\3\13\3\13\6\13\u0080"+
		"\n\13\r\13\16\13\u0081\3\13\3\13\5\13\u0086\n\13\3\13\3\13\3\13\3\f\3"+
		"\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\6\f\u0097\n\f\r\f\16\f\u0098"+
		"\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\6\r\u00ab"+
		"\n\r\r\r\16\r\u00ac\3\r\3\r\3\r\3\r\3\r\3\r\6\r\u00b5\n\r\r\r\16\r\u00b6"+
		"\3\r\3\r\3\r\5\r\u00bc\n\r\3\r\3\r\3\16\3\16\3\16\3\16\7\16\u00c4\n\16"+
		"\f\16\16\16\u00c7\13\16\3\16\3\16\3\16\3\16\3\16\3\16\7\16\u00cf\n\16"+
		"\f\16\16\16\u00d2\13\16\3\16\3\16\3\16\3\16\7\16\u00d8\n\16\f\16\16\16"+
		"\u00db\13\16\3\16\3\16\3\16\3\16\3\16\7\16\u00e2\n\16\f\16\16\16\u00e5"+
		"\13\16\5\16\u00e7\n\16\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\5\17\u00f1"+
		"\n\17\3\17\2\2\20\2\4\6\b\n\f\16\20\22\24\26\30\32\34\2\4\3\2\35 \3\2"+
		"\35\37\2\u00fe\2\36\3\2\2\2\4%\3\2\2\2\6)\3\2\2\2\b<\3\2\2\2\n>\3\2\2"+
		"\2\fJ\3\2\2\2\16L\3\2\2\2\20T\3\2\2\2\22\\\3\2\2\2\24d\3\2\2\2\26\u008a"+
		"\3\2\2\2\30\u009d\3\2\2\2\32\u00e6\3\2\2\2\34\u00f0\3\2\2\2\36\37\7\3"+
		"\2\2\37 \5\4\3\2 !\5\n\6\2!\"\7\4\2\2\"#\b\2\1\2#\3\3\2\2\2$&\5\6\4\2"+
		"%$\3\2\2\2&\'\3\2\2\2\'%\3\2\2\2\'(\3\2\2\2(\5\3\2\2\2)*\5\b\5\2*+\7\35"+
		"\2\2+\61\b\4\1\2,-\7\30\2\2-.\7\35\2\2.\60\b\4\1\2/,\3\2\2\2\60\63\3\2"+
		"\2\2\61/\3\2\2\2\61\62\3\2\2\2\62\64\3\2\2\2\63\61\3\2\2\2\64\65\7\25"+
		"\2\2\65\7\3\2\2\2\66\67\7\5\2\2\67=\b\5\1\289\7\6\2\29=\b\5\1\2:;\7\7"+
		"\2\2;=\b\5\1\2<\66\3\2\2\2<8\3\2\2\2<:\3\2\2\2=\t\3\2\2\2>@\b\6\1\2?A"+
		"\5\f\7\2@?\3\2\2\2AB\3\2\2\2B@\3\2\2\2BC\3\2\2\2C\13\3\2\2\2DK\5\16\b"+
		"\2EK\5\20\t\2FK\5\22\n\2GK\5\30\r\2HK\5\26\f\2IK\5\24\13\2JD\3\2\2\2J"+
		"E\3\2\2\2JF\3\2\2\2JG\3\2\2\2JH\3\2\2\2JI\3\2\2\2K\r\3\2\2\2LM\7\b\2\2"+
		"MN\7\21\2\2NO\7\35\2\2OP\b\b\1\2PQ\7\22\2\2QR\7\25\2\2RS\b\b\1\2S\17\3"+
		"\2\2\2TU\7\t\2\2UV\7\21\2\2VW\7\35\2\2WX\b\t\1\2XY\7\22\2\2YZ\7\25\2\2"+
		"Z[\b\t\1\2[\21\3\2\2\2\\]\7\35\2\2]^\b\n\1\2^_\7\27\2\2_`\b\n\1\2`a\5"+
		"\32\16\2ab\7\25\2\2bc\b\n\1\2c\23\3\2\2\2de\7\n\2\2ef\7\21\2\2fg\7\35"+
		"\2\2gh\b\13\1\2hi\7\22\2\2ix\7\31\2\2jk\7\13\2\2kl\t\2\2\2lm\b\13\1\2"+
		"mn\7\24\2\2np\b\13\1\2oq\5\f\7\2po\3\2\2\2qr\3\2\2\2rp\3\2\2\2rs\3\2\2"+
		"\2st\3\2\2\2tu\b\13\1\2uw\3\2\2\2vj\3\2\2\2wz\3\2\2\2xv\3\2\2\2xy\3\2"+
		"\2\2y\u0085\3\2\2\2zx\3\2\2\2{|\7\f\2\2|}\7\24\2\2}\177\b\13\1\2~\u0080"+
		"\5\f\7\2\177~\3\2\2\2\u0080\u0081\3\2\2\2\u0081\177\3\2\2\2\u0081\u0082"+
		"\3\2\2\2\u0082\u0083\3\2\2\2\u0083\u0084\b\13\1\2\u0084\u0086\3\2\2\2"+
		"\u0085{\3\2\2\2\u0085\u0086\3\2\2\2\u0086\u0087\3\2\2\2\u0087\u0088\7"+
		"\32\2\2\u0088\u0089\b\13\1\2\u0089\25\3\2\2\2\u008a\u008b\7\r\2\2\u008b"+
		"\u008c\7\21\2\2\u008c\u008d\7\35\2\2\u008d\u008e\b\f\1\2\u008e\u008f\7"+
		"\34\2\2\u008f\u0090\b\f\1\2\u0090\u0091\t\3\2\2\u0091\u0092\b\f\1\2\u0092"+
		"\u0093\7\22\2\2\u0093\u0094\7\31\2\2\u0094\u0096\b\f\1\2\u0095\u0097\5"+
		"\f\7\2\u0096\u0095\3\2\2\2\u0097\u0098\3\2\2\2\u0098\u0096\3\2\2\2\u0098"+
		"\u0099\3\2\2\2\u0099\u009a\3\2\2\2\u009a\u009b\7\32\2\2\u009b\u009c\b"+
		"\f\1\2\u009c\27\3\2\2\2\u009d\u009e\7\16\2\2\u009e\u009f\7\21\2\2\u009f"+
		"\u00a0\7\35\2\2\u00a0\u00a1\b\r\1\2\u00a1\u00a2\7\34\2\2\u00a2\u00a3\b"+
		"\r\1\2\u00a3\u00a4\t\3\2\2\u00a4\u00a5\b\r\1\2\u00a5\u00a6\7\22\2\2\u00a6"+
		"\u00a7\7\17\2\2\u00a7\u00a8\7\31\2\2\u00a8\u00aa\b\r\1\2\u00a9\u00ab\5"+
		"\f\7\2\u00aa\u00a9\3\2\2\2\u00ab\u00ac\3\2\2\2\u00ac\u00aa\3\2\2\2\u00ac"+
		"\u00ad\3\2\2\2\u00ad\u00ae\3\2\2\2\u00ae\u00af\7\32\2\2\u00af\u00bb\b"+
		"\r\1\2\u00b0\u00b1\7\20\2\2\u00b1\u00b2\7\31\2\2\u00b2\u00b4\b\r\1\2\u00b3"+
		"\u00b5\5\f\7\2\u00b4\u00b3\3\2\2\2\u00b5\u00b6\3\2\2\2\u00b6\u00b4\3\2"+
		"\2\2\u00b6\u00b7\3\2\2\2\u00b7\u00b8\3\2\2\2\u00b8\u00b9\7\32\2\2\u00b9"+
		"\u00ba\b\r\1\2\u00ba\u00bc\3\2\2\2\u00bb\u00b0\3\2\2\2\u00bb\u00bc\3\2"+
		"\2\2\u00bc\u00bd\3\2\2\2\u00bd\u00be\b\r\1\2\u00be\31\3\2\2\2\u00bf\u00c5"+
		"\5\34\17\2\u00c0\u00c1\7\26\2\2\u00c1\u00c2\b\16\1\2\u00c2\u00c4\5\32"+
		"\16\2\u00c3\u00c0\3\2\2\2\u00c4\u00c7\3\2\2\2\u00c5\u00c3\3\2\2\2\u00c5"+
		"\u00c6\3\2\2\2\u00c6\u00e7\3\2\2\2\u00c7\u00c5\3\2\2\2\u00c8\u00c9\7\21"+
		"\2\2\u00c9\u00d0\b\16\1\2\u00ca\u00cb\5\32\16\2\u00cb\u00cc\7\21\2\2\u00cc"+
		"\u00cd\b\16\1\2\u00cd\u00cf\3\2\2\2\u00ce\u00ca\3\2\2\2\u00cf\u00d2\3"+
		"\2\2\2\u00d0\u00ce\3\2\2\2\u00d0\u00d1\3\2\2\2\u00d1\u00d3\3\2\2\2\u00d2"+
		"\u00d0\3\2\2\2\u00d3\u00d9\5\32\16\2\u00d4\u00d5\7\22\2\2\u00d5\u00d6"+
		"\b\16\1\2\u00d6\u00d8\5\32\16\2\u00d7\u00d4\3\2\2\2\u00d8\u00db\3\2\2"+
		"\2\u00d9\u00d7\3\2\2\2\u00d9\u00da\3\2\2\2\u00da\u00dc\3\2\2\2\u00db\u00d9"+
		"\3\2\2\2\u00dc\u00dd\7\22\2\2\u00dd\u00e3\b\16\1\2\u00de\u00df\7\26\2"+
		"\2\u00df\u00e0\b\16\1\2\u00e0\u00e2\5\32\16\2\u00e1\u00de\3\2\2\2\u00e2"+
		"\u00e5\3\2\2\2\u00e3\u00e1\3\2\2\2\u00e3\u00e4\3\2\2\2\u00e4\u00e7\3\2"+
		"\2\2\u00e5\u00e3\3\2\2\2\u00e6\u00bf\3\2\2\2\u00e6\u00c8\3\2\2\2\u00e7"+
		"\33\3\2\2\2\u00e8\u00e9\7\35\2\2\u00e9\u00f1\b\17\1\2\u00ea\u00eb\7\36"+
		"\2\2\u00eb\u00f1\b\17\1\2\u00ec\u00ed\7\37\2\2\u00ed\u00f1\b\17\1\2\u00ee"+
		"\u00ef\7 \2\2\u00ef\u00f1\b\17\1\2\u00f0\u00e8\3\2\2\2\u00f0\u00ea\3\2"+
		"\2\2\u00f0\u00ec\3\2\2\2\u00f0\u00ee\3\2\2\2\u00f1\35\3\2\2\2\25\'\61"+
		"<BJrx\u0081\u0085\u0098\u00ac\u00b6\u00bb\u00c5\u00d0\u00d9\u00e3\u00e6"+
		"\u00f0";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}