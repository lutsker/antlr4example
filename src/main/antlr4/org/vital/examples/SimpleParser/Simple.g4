grammar Simple;

/* parser */

simple: group+ EOF;
group: hostsgroup ; 
hostsgroup : KWHOSTS (BLOCKOPEN BLOCKCLOSE | BLOCKOPEN hostdef BLOCKCLOSE);
hostdef : (KWHOST NAME EQ (BLOCKOPEN BLOCKCLOSE | BLOCKOPEN assignment BLOCKCLOSE) )+;
assignment : (NAME EQ HC NAME HC SC | NAME EQ HC HC SC | WHITESPACE)+;

/* lexer 
fragment A : ('A'|'a') ;
NEWLINE : ('\r'? '\n' | '\r')+ ;
TEXT : ('['|'(') ~[\])]+ (']'|')') ;
*/

HC : '"';
SC : ';';
EQ : '=';
BLOCKOPEN : '{';
BLOCKCLOSE : '}';
NAME : ([a-z] | '.' | '/' )([a-z] | '_' | [0-9] | '!' | '.' | '/' )* ;
KWHOST : 'H' 'O' 'S' 'T';
KWHOSTS : 'H' 'O' 'S' 'T' 'S';
ID : ([A-Z] | '_')+ ;
WHITESPACE : (' ' | '\t' | '\n')+ -> skip ;
