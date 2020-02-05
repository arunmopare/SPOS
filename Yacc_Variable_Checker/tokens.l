%{
#include<stdio.h>
#include "y.tab.h"
%}

DIGIT [0-9]
REAL {DIGIT}+[.]{DIGIT}*
LETTER [A-Za-z]
ASSIGN =

%%
[\t ] ;
int {printf("%s\t==>Integer\n",yytext);return (INT);}
float {printf("%s\t==>Float\n",yytext);return (FLOAT);}
char {printf("%s\t==>Character\n",yytext);return (CHAR);}
boolean {printf("%s\t==>Boolean\n",yytext);return (BOOL);}
true|false {printf("%s\t==>Boolean value\n",yytext);return BLVAL;}
[a-zA-Z]+[a-zA-Z0-9_]* {printf("%s\t==>Identifier\n",yytext);return ID;}
\n return NL;
['][^\t\n]['] {printf("%s\t==>Char value\n",yytext);return CHVAL;}
{REAL} {printf("%s\t==>Real no\n",yytext);return REAL;}
{DIGIT}+ {printf("%s\t==>Int no\n",yytext);return NUM;}
"," {printf("%s\t==>Comma\n",yytext);return COM;}
";" {printf("%s\t==>Delimiter\n",yytext);return DL;}
{ASSIGN} {printf("%s\t==>Assignment operator\n",yytext);return OP;}
. ;
%%

int yywrap(){
    return 1;
}
