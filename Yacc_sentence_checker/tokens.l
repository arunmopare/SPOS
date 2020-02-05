%{
#include "y.tab.h"
%}
%%
[\t ] ;
am|is|are|will|going|playing|can|have|goes|plays {printf("VERB\t==>%s\n",yytext);return VERB;}
He|She|They|I|You|We { printf("PRONOUN\t==>%s\n",yytext);return PRONOUN;}
and|but|if|or|also|then { printf("CONJUNCN\t==>%s\n",yytext);return CONJUNCN;}
in|on|to { printf("PREPOSN\t==>%s\n",yytext);return PREPOS;}
fast|good|honest { printf("ADJECTIVE\t==>%s\n",yytext);return ADJECTIVE;}
gently|simply { printf("ADVERB\t==>%s\n",yytext);return ADVERB;}
[a-zA-Z]+ { printf("NOUN\t==>%s\n",yytext);return NOUN;}
. ;
%%
int yywrap(){
    return 1;
}
