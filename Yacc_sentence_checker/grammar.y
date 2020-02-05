%{
#include<stdio.h>
void yyerror(char*);
int yylex();
FILE* yyin;
%}
%token VERB PRONOUN CONJUNCN NOUN PREPOS ADJECTIVE ADVERB

%%
s: simple {printf("It is a simple sentence\n");} | compound {printf("It is a compound sentence\n");};
simple: subject VERB object;
compound: subject VERB object CONJUNCN subject VERB object;
subject: NOUN|PRONOUN;
object: NOUN|ADJECTIVE NOUN|PREPOS NOUN|ADVERB NOUN;
%%

void yyerror(char *s){
    printf("Error: %s\n",s);
}

int main(){
    yyin = fopen("input.txt","r");
    yyparse();
    fclose(yyin);
}
