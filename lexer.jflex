package cup.example;
import java_cup.runtime.ComplexSymbolFactory;
import java_cup.runtime.ComplexSymbolFactory.Location;
import java_cup.runtime.Symbol;
import java.lang.*;
import java.io.InputStreamReader;

%%

%class Lexer
%implements sym
%public
%unicode
%line
%column
%cup
%char
%{
	

    public Lexer(ComplexSymbolFactory sf, java.io.InputStream is){
		this(is);
        symbolFactory = sf;
    }
	public Lexer(ComplexSymbolFactory sf, java.io.Reader reader){
		this(reader);
        symbolFactory = sf;
    }
    
    private StringBuffer sb;
    private ComplexSymbolFactory symbolFactory;
    private int csline,cscolumn;

    public Symbol symbol(String name, int code){
		return symbolFactory.newSymbol(name, code,
						new Location(yyline+1,yycolumn+1, yychar), // -yylength()
						new Location(yyline+1,yycolumn+yylength(), yychar+yylength())
				);
    }
    public Symbol symbol(String name, int code, String lexem){
	return symbolFactory.newSymbol(name, code, 
						new Location(yyline+1, yycolumn +1, yychar), 
						new Location(yyline+1,yycolumn+yylength(), yychar+yylength()), lexem);
    }
    
    protected void emit_warning(String message){
    	System.out.println("scanner warning: " + message + " at : 2 "+ 
    			(yyline+1) + " " + (yycolumn+1) + " " + yychar);
    }
    
    protected void emit_error(String message){
    	System.out.println("scanner error: " + message + " at : 2" + 
    			(yyline+1) + " " + (yycolumn+1) + " " + yychar);
    }
%}

Newline     = \r | \n | \r\n
Whitespace  = [ \t\f] | {Newline}
Digit       = [0-9]
Number      = [1-9][0-9]*
Punctuation = [!\"#\$%&\'()\*\+\,\-\.\/:;<=>\?@\[\]\\\^_`{}\~]
Character   = {Punctuation} | {Digit} | [A-Za-z]
String      = {Character}+

/* literals */

boolean     = true|false
integer     = {Number}


/* comments */
Comment = {HastagComment} | {EndOfLineComment}
HastagComment       = "#" [^\r\n]* {Newline}
EndOfLineComment    = "//" [^\r\n]* {Newline}

ident = ([:jletter:] | "_" ) ([:jletterdigit:] | [:jletter:] | "_" )*

IdentifierStartCharacter = {Letter}|_
IdentifierCharacter      = {Letter}|{Digit}|_
Identifier               = {IdentifierStartCharacter}{IdentifierCharacter}*

%eofval{
    return symbolFactory.newSymbol("EOF",sym.EOF);
%eofval}

%state CODESEG

%%  
/* int bool void true false if else while return cin cout */

/* keywords */
<YYINITIAL> "int"      {return symbolFactory.newSymbol("INTEGER", INTEGER); }
<YYINITIAL> "bool"     {return symbolFactory.newSymbol("BOOL",BOOL); }
<YYINITIAL> "void"     {return symbolFactory.newSymbol("VOID", VOID); }
<YYINITIAL> "true"     {return symbolFactory.newSymbol("TRUE", TRUE); }
<YYINITIAL> "false"    {return symbolFactory.newSymbol("FALSE", FALSE); }
<YYINITIAL> "if"	   {return symbolFactory.newSymbol("IF", IF); }
<YYINITIAL> "else"	   {return symbolFactory.newSymbol("ELSE",ELSE); }
<YYINITIAL> "while"    {return symbolFactory.newSymbol("WHILE", WHILE); }
<YYINITIAL> "return"   {return symbolFactory.newSymbol("RETURN", RETURN); }
<YYINITIAL> "cin"      {return symbolFactory.newSymbol("CIN", CIN); }
<YYINITIAL> "cout"	   {return symbolFactory.newSymbol("COUT", COUT); }
<YYINITIAL> {
  
{Whitespace} {                              }
"="		   { return symbolFactory.newSymbol("EQ", EQ); }

/* Arithmetic */
"+"          { return symbolFactory.newSymbol("PLUS", PLUS); }
"-"          { return symbolFactory.newSymbol("MINUS", MINUS); }
"*"          { return symbolFactory.newSymbol("TIMES", TIMES); }
"/"		     { return symbolFactory.newSymbol("DIVISION", DIVISION); }

/* Shift Operators */
"<<"        { return symbolFactory.newSymbol("LEFTSHIFT", LEFTSHIFT); }
">>"        { return symbolFactory.newSymbol("RIGHTSHIFT", RIGHTSHIFT); } 


/* Relational Operators */
"<"          { return symbolFactory.newSymbol("LESS", LESS); }
">"          { return symbolFactory.newSymbol("GREATER", GREATER); } 
"<=" 		 { return symbolFactory.newSymbol("LESSEQ", LESSEQ); }        /* Less than or equal to */
">="         { return symbolFactory.newSymbol("GREATEREQ", GREATEREQ); }  /* Greater than or equal to */
"==" 		 { return symbolFactory.newSymbol("EQEQ", EQEQ); }            /* Equal Equal */
"!="         { return symbolFactory.newSymbol("NOTEQ", NOTEQ); }          /* Not Equal */

/* Logical Operators */
"||"         { return symbolFactory.newSymbol("OR", OR); }
"&&"         { return symbolFactory.newSymbol("AND", AND); }
"!"          { return symbolFactory.newSymbol("NOT", NOT); }

/* Separators */
"{"		   { return symbolFactory.newSymbol("LEFTCURLY", LEFTCURLY); }
"}"		   { return symbolFactory.newSymbol("RIGHTCURLY", RIGHTCURLY); }
"("		   { return symbolFactory.newSymbol("LEFT", LEFT); }
")"		   { return symbolFactory.newSymbol("RIGHT", RIGHT); }
"["		   { return symbolFactory.newSymbol("LEFTSQ", LEFTSQ); }
"]"		   { return symbolFactory.newSymbol("RIGHTSQ", RIGHTSQ); }
","		   { return symbolFactory.newSymbol("COMMA", COMMA); }
";"        { return symbolFactory.newSymbol("SEMI", SEMI); }	

{Comment}    { 							  }
  
/* Literals */
{integer}         { return symbolFactory.newSymbol("INTEGER", INTEGER); }
{boolean}         { return symbolFactory.newSymbol("BOOLEAN", BOOLEAN); }
\"{String}*\"     { return symbolFactory.newSymbol("STRING", STRING); }
{Identifier}      { return symbolFactory.newSymbol("IDENTIFIER", IDENTIFIER); }
}



// error fallback
.|\n          { emit_warning("Unrecognized character '" +yytext()+"' -- ignored"); }
