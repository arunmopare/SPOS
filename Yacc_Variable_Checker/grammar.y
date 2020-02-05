%{
#include<stdio.h>
void yyerror(char*);
int yylex();
FILE* yyin;
%}
%token INT FLOAT CHAR BOOL BLVAL CHVAL ID NL REAL NUM COM DL OP

%%
s: forint|forfloat|forchar|forbool
;
forint: INT varint DL NL {printf("Valid integer declaration"); return 0;}
;
forfloat: FLOAT varfloat DL NL {printf("Valid float declaration"); return 0;}
;
forchar: CHAR varchar DL NL {printf("Valid char declaration"); return 0;}
;
forbool: BOOL varbool DL NL {printf("Valid boolean declaration"); return 0;}
;

varint: ID | ID COM varint | ID OP NUM | ID OP NUM COM varint |
;
varfloat: ID | ID COM varfloat | ID OP REAL | ID OP REAL COM varfloat |
;
varchar: ID | ID COM varchar | ID OP CHVAL | ID OP CHVAL COM varchar |
;
varbool: ID | ID COM varbool | ID OP BLVAL | ID OP BLVAL COM varbool |
;
%%
void yyerror(char *s){
    fprintf(stderr, "Error: %s\n",s);
}

int main(){
    yyin = fopen("input.txt","r");
    yyparse();
    fclose(yyin);
    return 0;    
}
