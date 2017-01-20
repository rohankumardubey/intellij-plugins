/* The following code was generated by JFlex 1.7.0-SNAPSHOT tweaked for IntelliJ platform */

package name.kropp.intellij.makefile;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;

import static com.intellij.psi.TokenType.BAD_CHARACTER;
import static com.intellij.psi.TokenType.WHITE_SPACE;
import static name.kropp.intellij.makefile.psi.MakefileTypes.*;


/**
 * This class is a scanner generated by 
 * <a href="http://www.jflex.de/">JFlex</a> 1.7.0-SNAPSHOT
 * from the specification file <tt>MakefileLexer.flex</tt>
 */
public class _MakefileLexer implements FlexLexer {

  /** This character denotes the end of file */
  public static final int YYEOF = -1;

  /** initial size of the lookahead buffer */
  private static final int ZZ_BUFFERSIZE = 16384;

  /** lexical states */
  public static final int YYINITIAL = 0;
  public static final int PREREQUISITES = 2;
  public static final int INCLUDES = 4;
  public static final int SOURCE = 6;
  public static final int DEFINE = 8;
  public static final int DEFINEBODY = 10;
  public static final int CONDITIONALS = 12;

  /**
   * ZZ_LEXSTATE[l] is the state in the DFA for the lexical state l
   * ZZ_LEXSTATE[l+1] is the state in the DFA for the lexical state l
   *                  at the beginning of a line
   * l is of the form l = 2*k, k a non negative integer
   */
  private static final int ZZ_LEXSTATE[] = { 
     0,  0,  1,  1,  2,  2,  3,  3,  4,  4,  5,  5,  6, 6
  };

  /** 
   * Translates characters to character classes
   * Chosen bits are [8, 6, 7]
   * Total runtime size is 1040 bytes
   */
  public static int ZZ_CMAP(int ch) {
    return ZZ_CMAP_A[ZZ_CMAP_Y[ZZ_CMAP_Z[ch>>13]|((ch>>7)&0x3f)]|(ch&0x7f)];
  }

  /* The ZZ_CMAP_Z table has 136 entries */
  static final char ZZ_CMAP_Z[] = zzUnpackCMap(
    "\1\0\207\100");

  /* The ZZ_CMAP_Y table has 128 entries */
  static final char ZZ_CMAP_Y[] = zzUnpackCMap(
    "\1\0\177\200");

  /* The ZZ_CMAP_A table has 256 entries */
  static final char ZZ_CMAP_A[] = zzUnpackCMap(
    "\11\0\1\14\1\1\2\0\1\4\22\0\1\2\1\12\1\0\1\5\7\0\1\13\1\0\1\24\14\0\1\6\1"+
    "\7\1\0\1\11\1\0\1\12\34\0\1\3\4\0\1\30\1\0\1\17\1\22\1\23\1\33\1\0\1\32\1"+
    "\15\2\0\1\20\1\0\1\16\1\35\1\27\1\34\1\36\1\25\1\31\1\21\1\26\1\0\1\37\3\0"+
    "\1\10\203\0");

  /** 
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\7\0\1\1\2\2\1\3\1\4\1\5\1\6\1\1"+
    "\1\7\10\1\1\10\1\1\1\6\1\11\1\12\1\2"+
    "\1\13\1\14\1\2\1\6\1\2\1\15\1\6\1\16"+
    "\1\6\1\1\1\17\1\6\1\17\1\20\1\2\1\0"+
    "\13\1\2\21\3\0\1\17\14\1\1\17\4\1\1\22"+
    "\3\1\1\23\4\1\1\17\2\1\1\24\1\25\2\1"+
    "\1\26\1\1\1\27\2\1\1\30\1\1\1\31\1\1"+
    "\1\32\1\33\2\1\1\34\1\1\1\35\1\1\1\36"+
    "\1\37";

  private static int [] zzUnpackAction() {
    int [] result = new int[115];
    int offset = 0;
    offset = zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAction(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /** 
   * Translates a state to a row index in the transition table
   */
  private static final int [] ZZ_ROWMAP = zzUnpackRowMap();

  private static final String ZZ_ROWMAP_PACKED_0 =
    "\0\0\0\40\0\100\0\140\0\200\0\240\0\300\0\340"+
    "\0\u0100\0\u0120\0\u0140\0\u0160\0\u0180\0\u01a0\0\u01c0\0\u01e0"+
    "\0\u0200\0\u0220\0\u0240\0\u0260\0\u0280\0\u02a0\0\u02c0\0\u02e0"+
    "\0\u0300\0\u0320\0\u0180\0\340\0\340\0\u0340\0\u0360\0\u0380"+
    "\0\u03a0\0\u03c0\0\u03e0\0\u0400\0\u0420\0\u0180\0\u0440\0\u0460"+
    "\0\u0480\0\u0480\0\u04a0\0\u04c0\0\u04e0\0\u01a0\0\u0500\0\u0520"+
    "\0\u0540\0\u0560\0\u0580\0\u05a0\0\u05c0\0\u05e0\0\u0600\0\u0620"+
    "\0\u0640\0\u0180\0\u0660\0\u0360\0\u0440\0\u0480\0\u0680\0\u06a0"+
    "\0\u06c0\0\u06e0\0\u0700\0\u0720\0\u0740\0\u0760\0\u0780\0\u07a0"+
    "\0\u07c0\0\u07e0\0\u0800\0\u0820\0\u0840\0\u0860\0\u0880\0\u08a0"+
    "\0\340\0\u08c0\0\u08e0\0\u0900\0\340\0\u0920\0\u0940\0\u0960"+
    "\0\u0980\0\u09a0\0\u09c0\0\u09e0\0\340\0\340\0\u0a00\0\u0a20"+
    "\0\340\0\u0a40\0\340\0\u0a60\0\u0a80\0\u0480\0\u0aa0\0\340"+
    "\0\u0ac0\0\340\0\340\0\u0ae0\0\u0b00\0\340\0\u0b20\0\340"+
    "\0\u0b40\0\340\0\340";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[115];
    int offset = 0;
    offset = zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackRowMap(String packed, int offset, int [] result) {
    int i = 0;  /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int high = packed.charAt(i++) << 16;
      result[j++] = high | packed.charAt(i++);
    }
    return j;
  }

  /** 
   * The transition table of the DFA
   */
  private static final int [] ZZ_TRANS = zzUnpackTrans();

  private static final String ZZ_TRANS_PACKED_0 =
    "\1\10\1\11\1\12\1\10\1\11\1\13\1\14\2\10"+
    "\1\15\1\16\1\17\1\20\1\21\3\10\1\22\1\23"+
    "\1\24\2\25\1\26\1\27\5\10\1\30\3\10\1\31"+
    "\1\12\1\32\1\31\1\13\1\33\1\34\1\35\2\33"+
    "\1\10\1\36\24\10\1\31\1\12\1\10\1\31\1\13"+
    "\1\33\2\10\2\33\1\10\1\36\23\10\1\37\1\40"+
    "\1\41\1\42\1\40\1\13\6\37\1\43\23\37\1\10"+
    "\1\44\1\12\1\10\1\44\1\13\1\45\2\10\1\46"+
    "\1\47\1\50\1\36\23\10\1\51\1\11\1\51\1\52"+
    "\1\11\1\13\15\51\1\53\14\51\1\54\1\40\1\55"+
    "\1\54\1\40\1\13\32\54\1\10\2\0\1\10\3\0"+
    "\2\10\2\0\1\10\1\0\23\10\1\0\1\11\2\0"+
    "\1\11\35\0\1\12\35\0\1\13\1\0\2\13\1\0"+
    "\33\13\6\0\1\56\2\0\1\15\77\0\1\15\26\0"+
    "\1\10\2\0\1\10\3\0\2\10\1\15\1\0\1\10"+
    "\1\0\23\10\14\0\1\20\23\0\1\10\2\0\1\10"+
    "\3\0\2\10\2\0\1\10\1\0\1\10\1\57\14\10"+
    "\1\60\5\10\2\0\1\10\3\0\2\10\2\0\1\10"+
    "\1\0\1\10\1\61\22\10\2\0\1\10\3\0\2\10"+
    "\2\0\1\10\1\0\6\10\1\62\15\10\2\0\1\10"+
    "\3\0\2\10\2\0\1\10\1\0\1\10\1\63\1\10"+
    "\1\64\16\10\1\65\1\10\2\0\1\10\3\0\2\10"+
    "\2\0\1\10\1\0\1\66\23\10\2\0\1\10\3\0"+
    "\2\10\2\0\1\10\1\0\12\10\1\67\11\10\2\0"+
    "\1\10\3\0\2\10\2\0\1\10\1\0\21\10\1\70"+
    "\2\10\2\0\1\10\3\0\2\10\2\0\1\10\1\0"+
    "\11\10\1\71\11\10\1\0\1\31\2\0\1\31\33\0"+
    "\1\10\1\72\1\0\1\10\1\73\2\0\2\10\2\0"+
    "\1\10\1\0\23\10\14\0\1\36\23\0\1\37\1\0"+
    "\1\37\1\74\1\0\33\37\1\0\1\40\2\0\1\40"+
    "\33\0\1\37\1\0\1\41\1\74\1\0\34\37\1\72"+
    "\1\37\1\74\1\73\34\37\1\0\1\37\1\74\1\0"+
    "\7\37\1\43\23\37\1\0\1\44\2\0\1\44\41\0"+
    "\1\75\2\0\1\46\37\0\1\46\26\0\1\10\2\0"+
    "\1\10\3\0\2\10\1\46\1\0\1\10\1\0\23\10"+
    "\1\51\1\0\1\51\1\76\1\0\34\51\1\0\1\51"+
    "\1\76\1\0\11\51\1\77\21\51\1\54\1\0\2\54"+
    "\2\0\33\54\1\0\1\55\1\54\2\0\32\54\1\10"+
    "\2\0\1\10\3\0\2\10\2\0\1\10\1\0\2\10"+
    "\1\100\21\10\2\0\1\10\3\0\2\10\2\0\1\10"+
    "\1\0\1\10\1\101\3\10\1\102\1\103\15\10\2\0"+
    "\1\10\3\0\2\10\2\0\1\10\1\0\5\10\1\104"+
    "\16\10\2\0\1\10\3\0\2\10\2\0\1\10\1\0"+
    "\16\10\1\105\5\10\2\0\1\10\3\0\2\10\2\0"+
    "\1\10\1\0\5\10\1\106\16\10\2\0\1\10\3\0"+
    "\2\10\2\0\1\10\1\0\10\10\1\107\13\10\2\0"+
    "\1\10\3\0\2\10\2\0\1\10\1\0\12\10\1\110"+
    "\11\10\2\0\1\10\3\0\2\10\2\0\1\10\1\0"+
    "\1\10\1\57\22\10\2\0\1\10\3\0\2\10\2\0"+
    "\1\10\1\0\13\10\1\111\10\10\2\0\1\10\3\0"+
    "\2\10\2\0\1\10\1\0\1\112\23\10\2\0\1\10"+
    "\3\0\2\10\2\0\1\10\1\0\6\10\1\113\14\10"+
    "\1\0\1\72\36\0\1\51\1\0\1\51\1\76\1\0"+
    "\15\51\1\114\15\51\1\10\2\0\1\10\3\0\2\10"+
    "\2\0\1\10\1\0\3\10\1\115\20\10\2\0\1\10"+
    "\3\0\2\10\2\0\1\10\1\0\5\10\1\116\1\117"+
    "\15\10\2\0\1\10\3\0\2\10\2\0\1\10\1\0"+
    "\6\10\1\120\15\10\2\0\1\10\3\0\2\10\2\0"+
    "\1\10\1\0\17\10\1\121\4\10\2\0\1\10\3\0"+
    "\2\10\2\0\1\10\1\0\6\10\1\122\15\10\2\0"+
    "\1\10\3\0\2\10\2\0\1\10\1\0\1\123\23\10"+
    "\2\0\1\10\3\0\2\10\2\0\1\10\1\0\1\124"+
    "\23\10\2\0\1\10\3\0\2\10\2\0\1\10\1\0"+
    "\6\10\1\125\15\10\2\0\1\10\3\0\2\10\2\0"+
    "\1\10\1\0\20\10\1\126\3\10\2\0\1\10\3\0"+
    "\2\10\2\0\1\10\1\0\14\10\1\127\7\10\2\0"+
    "\1\10\3\0\2\10\2\0\1\10\1\0\11\10\1\130"+
    "\12\10\2\0\1\10\3\0\2\10\2\0\1\10\1\0"+
    "\21\10\1\131\1\10\1\51\1\0\1\51\1\76\1\0"+
    "\16\51\1\132\14\51\1\10\2\0\1\10\3\0\2\10"+
    "\2\0\1\10\1\0\4\10\1\133\17\10\2\0\1\10"+
    "\3\0\2\10\2\0\1\10\1\0\6\10\1\134\15\10"+
    "\2\0\1\10\3\0\2\10\2\0\1\10\1\0\17\10"+
    "\1\135\4\10\2\0\1\10\3\0\2\10\2\0\1\10"+
    "\1\0\16\10\1\136\5\10\2\0\1\10\3\0\2\10"+
    "\2\0\1\10\1\0\16\10\1\137\5\10\2\0\1\10"+
    "\3\0\2\10\2\0\1\10\1\0\1\10\1\140\22\10"+
    "\2\0\1\10\3\0\2\10\2\0\1\10\1\0\16\10"+
    "\1\141\5\10\2\0\1\10\3\0\2\10\2\0\1\10"+
    "\1\0\21\10\1\142\2\10\2\0\1\10\3\0\2\10"+
    "\2\0\1\10\1\0\15\10\1\143\6\10\2\0\1\10"+
    "\3\0\2\10\2\0\1\10\1\0\13\10\1\144\10\10"+
    "\2\0\1\10\3\0\2\10\2\0\1\10\1\0\21\10"+
    "\1\145\1\10\1\51\1\0\1\51\1\76\1\0\26\51"+
    "\1\146\4\51\1\10\2\0\1\10\3\0\2\10\2\0"+
    "\1\10\1\0\5\10\1\147\16\10\2\0\1\10\3\0"+
    "\2\10\2\0\1\10\1\0\16\10\1\150\5\10\2\0"+
    "\1\10\3\0\2\10\2\0\1\10\1\0\1\151\23\10"+
    "\2\0\1\10\3\0\2\10\2\0\1\10\1\0\6\10"+
    "\1\152\15\10\2\0\1\10\3\0\2\10\2\0\1\10"+
    "\1\0\14\10\1\153\7\10\2\0\1\10\3\0\2\10"+
    "\2\0\1\10\1\0\14\10\1\154\7\10\2\0\1\10"+
    "\3\0\2\10\2\0\1\10\1\0\1\155\23\10\2\0"+
    "\1\10\3\0\2\10\2\0\1\10\1\0\6\10\1\156"+
    "\15\10\2\0\1\10\3\0\2\10\2\0\1\10\1\0"+
    "\1\10\1\157\22\10\2\0\1\10\3\0\2\10\2\0"+
    "\1\10\1\0\6\10\1\160\15\10\2\0\1\10\3\0"+
    "\2\10\2\0\1\10\1\0\5\10\1\161\16\10\2\0"+
    "\1\10\3\0\2\10\2\0\1\10\1\0\6\10\1\162"+
    "\15\10\2\0\1\10\3\0\2\10\2\0\1\10\1\0"+
    "\6\10\1\163\14\10";

  private static int [] zzUnpackTrans() {
    int [] result = new int[2912];
    int offset = 0;
    offset = zzUnpackTrans(ZZ_TRANS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackTrans(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      value--;
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /* error codes */
  private static final int ZZ_UNKNOWN_ERROR = 0;
  private static final int ZZ_NO_MATCH = 1;
  private static final int ZZ_PUSHBACK_2BIG = 2;

  /* error messages for the codes above */
  private static final String[] ZZ_ERROR_MSG = {
    "Unknown internal scanner error",
    "Error: could not match input",
    "Error: pushback value was too large"
  };

  /**
   * ZZ_ATTRIBUTE[aState] contains the attributes of state <code>aState</code>
   */
  private static final int [] ZZ_ATTRIBUTE = zzUnpackAttribute();

  private static final String ZZ_ATTRIBUTE_PACKED_0 =
    "\7\0\5\1\1\11\15\1\1\11\12\1\1\11\7\1"+
    "\1\0\13\1\1\11\1\1\3\0\65\1";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[115];
    int offset = 0;
    offset = zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAttribute(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /** the input device */
  private java.io.Reader zzReader;

  /** the current state of the DFA */
  private int zzState;

  /** the current lexical state */
  private int zzLexicalState = YYINITIAL;

  /** this buffer contains the current text to be matched and is
      the source of the yytext() string */
  private CharSequence zzBuffer = "";

  /** the textposition at the last accepting state */
  private int zzMarkedPos;

  /** the current text position in the buffer */
  private int zzCurrentPos;

  /** startRead marks the beginning of the yytext() string in the buffer */
  private int zzStartRead;

  /** endRead marks the last character in the buffer, that has been read
      from input */
  private int zzEndRead;

  /**
   * zzAtBOL == true <=> the scanner is currently at the beginning of a line
   */
  private boolean zzAtBOL = true;

  /** zzAtEOF == true <=> the scanner is at the EOF */
  private boolean zzAtEOF;

  /** denotes if the user-EOF-code has already been executed */
  private boolean zzEOFDone;

  /* user code: */
  public _MakefileLexer() {
    this((java.io.Reader)null);
  }


  /**
   * Creates a new scanner
   *
   * @param   in  the java.io.Reader to read input from.
   */
  public _MakefileLexer(java.io.Reader in) {
    this.zzReader = in;
  }


  /** 
   * Unpacks the compressed character translation table.
   *
   * @param packed   the packed character translation table
   * @return         the unpacked character translation table
   */
  private static char [] zzUnpackCMap(String packed) {
    int size = 0;
    for (int i = 0, length = packed.length(); i < length; i += 2) {
      size += packed.charAt(i);
    }
    char[] map = new char[size];
    int i = 0;  /* index in packed string  */
    int j = 0;  /* index in unpacked array */
    while (i < packed.length()) {
      int  count = packed.charAt(i++);
      char value = packed.charAt(i++);
      do map[j++] = value; while (--count > 0);
    }
    return map;
  }

  public final int getTokenStart() {
    return zzStartRead;
  }

  public final int getTokenEnd() {
    return getTokenStart() + yylength();
  }

  public void reset(CharSequence buffer, int start, int end, int initialState) {
    zzBuffer = buffer;
    zzCurrentPos = zzMarkedPos = zzStartRead = start;
    zzAtEOF  = false;
    zzAtBOL = true;
    zzEndRead = end;
    yybegin(initialState);
  }

  /**
   * Refills the input buffer.
   *
   * @return      <code>false</code>, iff there was new input.
   *
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  private boolean zzRefill() throws java.io.IOException {
    return true;
  }


  /**
   * Returns the current lexical state.
   */
  public final int yystate() {
    return zzLexicalState;
  }


  /**
   * Enters a new lexical state
   *
   * @param newState the new lexical state
   */
  public final void yybegin(int newState) {
    zzLexicalState = newState;
  }


  /**
   * Returns the text matched by the current regular expression.
   */
  public final CharSequence yytext() {
    return zzBuffer.subSequence(zzStartRead, zzMarkedPos);
  }


  /**
   * Returns the character at position <tt>pos</tt> from the
   * matched text.
   *
   * It is equivalent to yytext().charAt(pos), but faster
   *
   * @param pos the position of the character to fetch.
   *            A value from 0 to yylength()-1.
   *
   * @return the character at position pos
   */
  public final char yycharat(int pos) {
    return zzBuffer.charAt(zzStartRead+pos);
  }


  /**
   * Returns the length of the matched text region.
   */
  public final int yylength() {
    return zzMarkedPos-zzStartRead;
  }


  /**
   * Reports an error that occured while scanning.
   *
   * In a wellformed scanner (no or only correct usage of
   * yypushback(int) and a match-all fallback rule) this method
   * will only be called with things that "Can't Possibly Happen".
   * If this method is called, something is seriously wrong
   * (e.g. a JFlex bug producing a faulty scanner etc.).
   *
   * Usual syntax/scanner level error handling should be done
   * in error fallback rules.
   *
   * @param   errorCode  the code of the errormessage to display
   */
  private void zzScanError(int errorCode) {
    String message;
    try {
      message = ZZ_ERROR_MSG[errorCode];
    }
    catch (ArrayIndexOutOfBoundsException e) {
      message = ZZ_ERROR_MSG[ZZ_UNKNOWN_ERROR];
    }

    throw new Error(message);
  }


  /**
   * Pushes the specified amount of characters back into the input stream.
   *
   * They will be read again by then next call of the scanning method
   *
   * @param number  the number of characters to be read again.
   *                This number must not be greater than yylength()!
   */
  public void yypushback(int number)  {
    if ( number > yylength() )
      zzScanError(ZZ_PUSHBACK_2BIG);

    zzMarkedPos -= number;
  }


  /**
   * Resumes scanning until the next regular expression is matched,
   * the end of input is encountered or an I/O-Error occurs.
   *
   * @return      the next token
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  public IElementType advance() throws java.io.IOException {
    int zzInput;
    int zzAction;

    // cached fields:
    int zzCurrentPosL;
    int zzMarkedPosL;
    int zzEndReadL = zzEndRead;
    CharSequence zzBufferL = zzBuffer;

    int [] zzTransL = ZZ_TRANS;
    int [] zzRowMapL = ZZ_ROWMAP;
    int [] zzAttrL = ZZ_ATTRIBUTE;

    while (true) {
      zzMarkedPosL = zzMarkedPos;

      zzAction = -1;

      zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;

      zzState = ZZ_LEXSTATE[zzLexicalState];

      // set up zzAction for empty match case:
      int zzAttributes = zzAttrL[zzState];
      if ( (zzAttributes & 1) == 1 ) {
        zzAction = zzState;
      }


      zzForAction: {
        while (true) {

          if (zzCurrentPosL < zzEndReadL) {
            zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL/*, zzEndReadL*/);
            zzCurrentPosL += Character.charCount(zzInput);
          }
          else if (zzAtEOF) {
            zzInput = YYEOF;
            break zzForAction;
          }
          else {
            // store back cached positions
            zzCurrentPos  = zzCurrentPosL;
            zzMarkedPos   = zzMarkedPosL;
            boolean eof = zzRefill();
            // get translated positions and possibly new buffer
            zzCurrentPosL  = zzCurrentPos;
            zzMarkedPosL   = zzMarkedPos;
            zzBufferL      = zzBuffer;
            zzEndReadL     = zzEndRead;
            if (eof) {
              zzInput = YYEOF;
              break zzForAction;
            }
            else {
              zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL/*, zzEndReadL*/);
              zzCurrentPosL += Character.charCount(zzInput);
            }
          }
          int zzNext = zzTransL[ zzRowMapL[zzState] + ZZ_CMAP(zzInput) ];
          if (zzNext == -1) break zzForAction;
          zzState = zzNext;

          zzAttributes = zzAttrL[zzState];
          if ( (zzAttributes & 1) == 1 ) {
            zzAction = zzState;
            zzMarkedPosL = zzCurrentPosL;
            if ( (zzAttributes & 8) == 8 ) break zzForAction;
          }

        }
      }

      // store back cached position
      zzMarkedPos = zzMarkedPosL;

      if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
        zzAtEOF = true;
        switch (zzLexicalState) {
            case PREREQUISITES: {
              yypushback(yylength()); yybegin(YYINITIAL); return EOL;
            }
            case 116: break;
            case INCLUDES: {
              yypushback(yylength()); yybegin(YYINITIAL); return EOL;
            }
            case 117: break;
            default:
        return null;
        }
      }
      else {
        switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
          case 1: 
            { return IDENTIFIER;
            }
          case 32: break;
          case 2: 
            { return WHITE_SPACE;
            }
          case 33: break;
          case 3: 
            { return COMMENT;
            }
          case 34: break;
          case 4: 
            { yybegin(PREREQUISITES); return COLON;
            }
          case 35: break;
          case 5: 
            { yybegin(SOURCE); return ASSIGN;
            }
          case 36: break;
          case 6: 
            { return BAD_CHARACTER;
            }
          case 37: break;
          case 7: 
            { yybegin(SOURCE); return TAB;
            }
          case 38: break;
          case 8: 
            { yybegin(YYINITIAL); return EOL;
            }
          case 39: break;
          case 9: 
            { yybegin(SOURCE); return SEMICOLON;
            }
          case 40: break;
          case 10: 
            { return PIPE;
            }
          case 41: break;
          case 11: 
            { return LINE;
            }
          case 42: break;
          case 12: 
            { yybegin(YYINITIAL); return WHITE_SPACE;
            }
          case 43: break;
          case 13: 
            { yybegin(DEFINEBODY); return WHITE_SPACE;
            }
          case 44: break;
          case 14: 
            { return ASSIGN;
            }
          case 45: break;
          case 15: 
            { return VARIABLE_VALUE_LINE;
            }
          case 46: break;
          case 16: 
            { yybegin(YYINITIAL); return CONDITION;
            }
          case 47: break;
          case 17: 
            { return SPLIT;
            }
          case 48: break;
          case 18: 
            { yybegin(CONDITIONALS); return KEYWORD_IFEQ;
            }
          case 49: break;
          case 19: 
            { return KEYWORD_ELSE;
            }
          case 50: break;
          case 20: 
            { yybegin(CONDITIONALS); return KEYWORD_IFNEQ;
            }
          case 51: break;
          case 21: 
            { yybegin(CONDITIONALS); return KEYWORD_IFDEF;
            }
          case 52: break;
          case 22: 
            { return KEYWORD_ENDIF;
            }
          case 53: break;
          case 23: 
            { yybegin(INCLUDES); return KEYWORD_VPATH;
            }
          case 54: break;
          case 24: 
            { yybegin(YYINITIAL); return KEYWORD_ENDEF;
            }
          case 55: break;
          case 25: 
            { yybegin(CONDITIONALS); return KEYWORD_IFNDEF;
            }
          case 56: break;
          case 26: 
            { yybegin(DEFINE); return KEYWORD_DEFINE;
            }
          case 57: break;
          case 27: 
            { return KEYWORD_EXPORT;
            }
          case 58: break;
          case 28: 
            { yybegin(INCLUDES); return KEYWORD_INCLUDE;
            }
          case 59: break;
          case 29: 
            { return KEYWORD_PRIVATE;
            }
          case 60: break;
          case 30: 
            { yybegin(INCLUDES); return KEYWORD_UNDEFINE;
            }
          case 61: break;
          case 31: 
            { return KEYWORD_OVERRIDE;
            }
          case 62: break;
          default:
            zzScanError(ZZ_NO_MATCH);
          }
      }
    }
  }


}
