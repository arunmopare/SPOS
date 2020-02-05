%{
    int nlines,nwords,nchars;
%}

%%
\n {nchars++;nlines++;}
[^ \n\t]+ {nwords++;nchars+=yyleng;}
. {nchars++;}

%%

int yywrap(){
    return 1;
}

int main(int argc, char* argv[]){
    yyin = fopen(argv[1],"r");
    yylex();
    printf("No. of lines: %d\nNo. of characters: %d\nNo. of words: %d\n",nlines,(nchars-1),nwords); 
}
