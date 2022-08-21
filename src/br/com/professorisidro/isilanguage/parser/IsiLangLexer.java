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

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class IsiLangLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, AP=17, 
		FP=18, ASP=19, DP=20, SC=21, OP=22, ATTR=23, VIR=24, ACH=25, FCH=26, OPLOG=27, 
		OPREL=28, BOOL=29, ID=30, INTEGER=31, DOUBLE=32, TEXT=33, CHAR=34, WS=35;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
		"T__9", "T__10", "T__11", "T__12", "T__13", "T__14", "T__15", "AP", "FP", 
		"ASP", "DP", "SC", "OP", "ATTR", "VIR", "ACH", "FCH", "OPLOG", "OPREL", 
		"BOOL", "ID", "INTEGER", "DOUBLE", "TEXT", "CHAR", "WS"
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


	public IsiLangLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "IsiLang.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2%\u0116\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5"+
		"\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3"+
		"\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n"+
		"\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3"+
		"\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16\3"+
		"\16\3\16\3\16\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3"+
		"\21\3\21\3\21\3\21\3\22\3\22\3\23\3\23\3\24\3\24\3\25\3\25\3\26\3\26\3"+
		"\27\3\27\3\30\3\30\3\30\3\31\3\31\3\32\3\32\3\33\3\33\3\34\3\34\3\34\3"+
		"\34\3\34\5\34\u00d5\n\34\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35"+
		"\5\35\u00e0\n\35\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\5\36\u00eb"+
		"\n\36\3\37\3\37\7\37\u00ef\n\37\f\37\16\37\u00f2\13\37\3 \6 \u00f5\n "+
		"\r \16 \u00f6\3!\6!\u00fa\n!\r!\16!\u00fb\3!\3!\6!\u0100\n!\r!\16!\u0101"+
		"\5!\u0104\n!\3\"\3\"\7\"\u0108\n\"\f\"\16\"\u010b\13\"\3\"\3\"\3#\3#\3"+
		"#\3#\3$\3$\3$\3$\2\2%\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27"+
		"\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33"+
		"\65\34\67\359\36;\37= ?!A\"C#E$G%\3\2\n\5\2,-//\61\61\4\2>>@@\3\2c|\5"+
		"\2\62;C\\c|\3\2\62;\5\2\f\f\17\17$$\6\2\f\f\17\17))^^\5\2\13\f\17\17\""+
		"\"\2\u0122\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2"+
		"\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27"+
		"\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2"+
		"\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2"+
		"\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2"+
		"\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2"+
		"\2G\3\2\2\2\3I\3\2\2\2\5R\3\2\2\2\7[\3\2\2\2\tc\3\2\2\2\13i\3\2\2\2\r"+
		"n\3\2\2\2\17x\3\2\2\2\21\u0081\3\2\2\2\23\u0086\3\2\2\2\25\u008e\3\2\2"+
		"\2\27\u0096\3\2\2\2\31\u009b\3\2\2\2\33\u00a2\3\2\2\2\35\u00ab\3\2\2\2"+
		"\37\u00ae\3\2\2\2!\u00b4\3\2\2\2#\u00ba\3\2\2\2%\u00bc\3\2\2\2\'\u00be"+
		"\3\2\2\2)\u00c0\3\2\2\2+\u00c2\3\2\2\2-\u00c4\3\2\2\2/\u00c6\3\2\2\2\61"+
		"\u00c9\3\2\2\2\63\u00cb\3\2\2\2\65\u00cd\3\2\2\2\67\u00d4\3\2\2\29\u00df"+
		"\3\2\2\2;\u00ea\3\2\2\2=\u00ec\3\2\2\2?\u00f4\3\2\2\2A\u00f9\3\2\2\2C"+
		"\u0105\3\2\2\2E\u010e\3\2\2\2G\u0112\3\2\2\2IJ\7r\2\2JK\7t\2\2KL\7q\2"+
		"\2LM\7i\2\2MN\7t\2\2NO\7c\2\2OP\7o\2\2PQ\7c\2\2Q\4\3\2\2\2RS\7h\2\2ST"+
		"\7k\2\2TU\7o\2\2UV\7r\2\2VW\7t\2\2WX\7q\2\2XY\7i\2\2YZ\7=\2\2Z\6\3\2\2"+
		"\2[\\\7k\2\2\\]\7p\2\2]^\7v\2\2^_\7g\2\2_`\7k\2\2`a\7t\2\2ab\7q\2\2b\b"+
		"\3\2\2\2cd\7v\2\2de\7g\2\2ef\7z\2\2fg\7v\2\2gh\7q\2\2h\n\3\2\2\2ij\7t"+
		"\2\2jk\7g\2\2kl\7c\2\2lm\7n\2\2m\f\3\2\2\2no\7e\2\2op\7c\2\2pq\7t\2\2"+
		"qr\7c\2\2rs\7e\2\2st\7v\2\2tu\7g\2\2uv\7t\2\2vw\7g\2\2w\16\3\2\2\2xy\7"+
		"d\2\2yz\7q\2\2z{\7q\2\2{|\7n\2\2|}\7g\2\2}~\7c\2\2~\177\7p\2\2\177\u0080"+
		"\7q\2\2\u0080\20\3\2\2\2\u0081\u0082\7n\2\2\u0082\u0083\7g\2\2\u0083\u0084"+
		"\7k\2\2\u0084\u0085\7c\2\2\u0085\22\3\2\2\2\u0086\u0087\7g\2\2\u0087\u0088"+
		"\7u\2\2\u0088\u0089\7e\2\2\u0089\u008a\7t\2\2\u008a\u008b\7g\2\2\u008b"+
		"\u008c\7x\2\2\u008c\u008d\7c\2\2\u008d\24\3\2\2\2\u008e\u008f\7g\2\2\u008f"+
		"\u0090\7u\2\2\u0090\u0091\7e\2\2\u0091\u0092\7q\2\2\u0092\u0093\7n\2\2"+
		"\u0093\u0094\7j\2\2\u0094\u0095\7c\2\2\u0095\26\3\2\2\2\u0096\u0097\7"+
		"e\2\2\u0097\u0098\7c\2\2\u0098\u0099\7u\2\2\u0099\u009a\7q\2\2\u009a\30"+
		"\3\2\2\2\u009b\u009c\7r\2\2\u009c\u009d\7c\2\2\u009d\u009e\7f\2\2\u009e"+
		"\u009f\7t\2\2\u009f\u00a0\7c\2\2\u00a0\u00a1\7q\2\2\u00a1\32\3\2\2\2\u00a2"+
		"\u00a3\7g\2\2\u00a3\u00a4\7p\2\2\u00a4\u00a5\7s\2\2\u00a5\u00a6\7w\2\2"+
		"\u00a6\u00a7\7c\2\2\u00a7\u00a8\7p\2\2\u00a8\u00a9\7v\2\2\u00a9\u00aa"+
		"\7q\2\2\u00aa\34\3\2\2\2\u00ab\u00ac\7u\2\2\u00ac\u00ad\7g\2\2\u00ad\36"+
		"\3\2\2\2\u00ae\u00af\7g\2\2\u00af\u00b0\7p\2\2\u00b0\u00b1\7v\2\2\u00b1"+
		"\u00b2\7c\2\2\u00b2\u00b3\7q\2\2\u00b3 \3\2\2\2\u00b4\u00b5\7u\2\2\u00b5"+
		"\u00b6\7g\2\2\u00b6\u00b7\7p\2\2\u00b7\u00b8\7c\2\2\u00b8\u00b9\7q\2\2"+
		"\u00b9\"\3\2\2\2\u00ba\u00bb\7*\2\2\u00bb$\3\2\2\2\u00bc\u00bd\7+\2\2"+
		"\u00bd&\3\2\2\2\u00be\u00bf\7$\2\2\u00bf(\3\2\2\2\u00c0\u00c1\7<\2\2\u00c1"+
		"*\3\2\2\2\u00c2\u00c3\7\60\2\2\u00c3,\3\2\2\2\u00c4\u00c5\t\2\2\2\u00c5"+
		".\3\2\2\2\u00c6\u00c7\7<\2\2\u00c7\u00c8\7?\2\2\u00c8\60\3\2\2\2\u00c9"+
		"\u00ca\7.\2\2\u00ca\62\3\2\2\2\u00cb\u00cc\7}\2\2\u00cc\64\3\2\2\2\u00cd"+
		"\u00ce\7\177\2\2\u00ce\66\3\2\2\2\u00cf\u00d0\7(\2\2\u00d0\u00d5\7(\2"+
		"\2\u00d1\u00d2\7~\2\2\u00d2\u00d5\7~\2\2\u00d3\u00d5\7#\2\2\u00d4\u00cf"+
		"\3\2\2\2\u00d4\u00d1\3\2\2\2\u00d4\u00d3\3\2\2\2\u00d58\3\2\2\2\u00d6"+
		"\u00e0\t\3\2\2\u00d7\u00d8\7@\2\2\u00d8\u00e0\7?\2\2\u00d9\u00da\7>\2"+
		"\2\u00da\u00e0\7?\2\2\u00db\u00dc\7?\2\2\u00dc\u00e0\7?\2\2\u00dd\u00de"+
		"\7#\2\2\u00de\u00e0\7?\2\2\u00df\u00d6\3\2\2\2\u00df\u00d7\3\2\2\2\u00df"+
		"\u00d9\3\2\2\2\u00df\u00db\3\2\2\2\u00df\u00dd\3\2\2\2\u00e0:\3\2\2\2"+
		"\u00e1\u00e2\7v\2\2\u00e2\u00e3\7t\2\2\u00e3\u00e4\7w\2\2\u00e4\u00eb"+
		"\7g\2\2\u00e5\u00e6\7h\2\2\u00e6\u00e7\7c\2\2\u00e7\u00e8\7n\2\2\u00e8"+
		"\u00e9\7u\2\2\u00e9\u00eb\7g\2\2\u00ea\u00e1\3\2\2\2\u00ea\u00e5\3\2\2"+
		"\2\u00eb<\3\2\2\2\u00ec\u00f0\t\4\2\2\u00ed\u00ef\t\5\2\2\u00ee\u00ed"+
		"\3\2\2\2\u00ef\u00f2\3\2\2\2\u00f0\u00ee\3\2\2\2\u00f0\u00f1\3\2\2\2\u00f1"+
		">\3\2\2\2\u00f2\u00f0\3\2\2\2\u00f3\u00f5\t\6\2\2\u00f4\u00f3\3\2\2\2"+
		"\u00f5\u00f6\3\2\2\2\u00f6\u00f4\3\2\2\2\u00f6\u00f7\3\2\2\2\u00f7@\3"+
		"\2\2\2\u00f8\u00fa\t\6\2\2\u00f9\u00f8\3\2\2\2\u00fa\u00fb\3\2\2\2\u00fb"+
		"\u00f9\3\2\2\2\u00fb\u00fc\3\2\2\2\u00fc\u0103\3\2\2\2\u00fd\u00ff\7\60"+
		"\2\2\u00fe\u0100\t\6\2\2\u00ff\u00fe\3\2\2\2\u0100\u0101\3\2\2\2\u0101"+
		"\u00ff\3\2\2\2\u0101\u0102\3\2\2\2\u0102\u0104\3\2\2\2\u0103\u00fd\3\2"+
		"\2\2\u0103\u0104\3\2\2\2\u0104B\3\2\2\2\u0105\u0109\5\'\24\2\u0106\u0108"+
		"\n\7\2\2\u0107\u0106\3\2\2\2\u0108\u010b\3\2\2\2\u0109\u0107\3\2\2\2\u0109"+
		"\u010a\3\2\2\2\u010a\u010c\3\2\2\2\u010b\u0109\3\2\2\2\u010c\u010d\5\'"+
		"\24\2\u010dD\3\2\2\2\u010e\u010f\7)\2\2\u010f\u0110\n\b\2\2\u0110\u0111"+
		"\7)\2\2\u0111F\3\2\2\2\u0112\u0113\t\t\2\2\u0113\u0114\3\2\2\2\u0114\u0115"+
		"\b$\2\2\u0115H\3\2\2\2\r\2\u00d4\u00df\u00ea\u00ee\u00f0\u00f6\u00fb\u0101"+
		"\u0103\u0109\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}