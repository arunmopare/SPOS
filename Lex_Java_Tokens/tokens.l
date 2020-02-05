%{

FILE* yyin;
%}
DIGIT [0-9]
NUMBER {DIGIT}+
REAL {DIGIT}*[.]{NUMBER}
TEXT [A-Za-z]
KEYWORDS "class"|"static"
DATATYPE "int"|"float"|"double"|"boolean"|"String"|"void"
CONDITIONAL "if"|"else"|"else if"|"switch"|"case"
ITERATIVE "for"|"while"|"do"
DL ";"
PREPROCESSOR "import"[^\n]*";"
IDENTIFIER {TEXT}({DIGIT}|{TEXT}|"_")*
NONIDENTIFIER {DIGIT}({TEXT}|{DIGIT}|"_")*
ARITH_OP "+"|"-"|"*"|"/"|"%"
LOGICAL_OP "&&"|"||"|"!"|"!="
REL_OP "<"|">"|"<="|">="|"=="
UNARY_OP "++"|"--"
ACCESS_SP "public"|"private"|"protected"|""
FUNCTION {ACCESS_SP}{DATATYPE}{IDENTIFIER}"("({DATATYPE}{IDENTIFIER})*")"

%%
[ \n\t]+ ;
{PREPROCESSOR} {printf("%s\t==>Preprocessor\n",yytext);}
{CONDITIONAL} { printf("%s\t==> CONDITIONAL\n",yytext);}
{ITERATIVE} {printf("%s\t==> ITERATIVE CONSTRUCT\n",yytext);}
{DATATYPE}  {printf("%s\t==> DATATYPE\n",yytext);}
{ACCESS_SP} {printf("%s\t==> ACCESS SPECIFIER\n",yytext);}
{KEYWORDS} {printf("%s\t==> KEYWORDS\n",yytext);}
{IDENTIFIER} {printf("%s\t==> IDENTIFIER\n",yytext);}
{REAL} {printf("%s\t==> REAL CONSTANT\n",yytext);}
{NUMBER} {printf("%s\t==> CONSTAINT INTEGER\n",yytext);}
{NONIDENTIFIER} {printf("%s\t==> NONIDENTIFIER\n",yytext);}
{DL} {printf("%s\t==> DELIMITER\n",yytext);}
{UNARY_OP} {printf("%s\t==> UNARY OP\n",yytext);}
{ARITH_OP} {printf("%s\t==> ARITHMETIC OPERATOR\n",yytext);}
{LOGICAL_OP} {printf("%s\t==> LOGICAL OP\n",yytext);}
{REL_OP} {printf("%s\t==> RELATIONAL OP\n",yytext);}
"=" {printf("%s\t==> ASSIGNMENT OP\n",yytext);}
"{"  {printf("%s\t==> BLOCK BEGIN\n",yytext);}
"}"  {printf("%s\t==> BLOCK END\n",yytext);}
"("  {printf("%s\t==> PARANTHESIS BEGIN\n",yytext);}
")"  {printf("%s\t==> PARENTHESIS END\n",yytext);}
. ;

%%
int yywrap(){
    return 1;
}

int main(int argc, char* argv[]){
    yyin = fopen(argv[1],"r");
    yylex();
    fclose(yyin);
}
