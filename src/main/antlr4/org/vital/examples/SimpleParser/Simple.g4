grammar Simple;

/* parser */

simple: group+ EOF;
group: hostsgroup ; 
hostsgroup : KWHOSTS (BO BC | BO hostdef BC);
hostdef : (KWHOST NAME EQ (BO BC | BO assignment BC) )+;
assignment : (NAME EQ STRING SC | WHITESPACE)+;

/* lexer 

LIST {
SIGNAL component::signal = {
name = "";
value = "";
}


fragment A : ('A'|'a') ;
fragment S : ('S'|'s') ;
fragment Y : ('Y'|'y') ;
fragment H : ('H'|'h') ;
fragment O : ('O'|'o') ;
fragment U : ('U'|'u') ;
fragment T : ('T'|'t') ;

fragment LOWERCASE : [a-z] ;
fragment UPPERCASE : [A-Z] ;

SAYS : S A Y S ;
SHOUTS : S H O U T S;
WORD : (LOWERCASE | UPPERCASE | '_')+ ;
WHITESPACE : (' '|'\t');
NEWLINE : ('\r'? '\n' | '\r')+ ;
TEXT : ('['|'(') ~[\])]+ (']'|')') ;
*/

fragment LOWERCASE : [a-z];
fragment UPPERCASE : [A-Z];
fragment DIGIT : [0-9];


SC : ';';
EQ : '=';
// HC : '"';
BO : '{';
BC : '}';
NAME : (LOWERCASE )(LOWERCASE | '_' | DIGIT | '-' )* ;
KWHOST : 'H' 'O' 'S' 'T';
KWHOSTS : 'H' 'O' 'S' 'T' 'S';
STRING : '"' .* '"' ;
WHITESPACE : (' ' | '\t' | '\n')+ -> skip ;
