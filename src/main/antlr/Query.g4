/*
Copyright (c) 2019, Michael Mollard
*/

grammar Query;

@header {
package com.example.query;
}

input
   : query EOF
   ;

query
   : query (AND | OR) query
   | LPAREN query RPAREN
   | criteria
   ;

criteria
   : key op value
   ;

key
   : IDENTIFIER
   ;

value
   : IDENTIFIER
   | string
   | NUMBER
   | bool
   ;

op
   : EQ
   | GT
   | LT
   | CONTAINS
   | START_WITH
   ;

bool
    : 'true'
    | 'false'
    ;

string
    : SINGLE_STRING
    | DOUBLE_STRING
    ;

SINGLE_STRING
    : '\'' ~('\'')+ '\''
    ;

DOUBLE_STRING
    : '"' ~('"')+ '"'
    ;

AND
   : 'AND'
   ;

OR
   : 'OR'
   ;

NUMBER
   : ('0' .. '9') ('0' .. '9')* POINT? ('0' .. '9')*
   ;

LPAREN
   : '('
   ;


RPAREN
   : ')'
   ;


GT
   : '>'
   ;


LT
   : '<'
   ;


EQ
   : '='
   ;

CONTAINS
   : ':'
   ;

START_WITH
   : '@'
   ;

fragment POINT
   : '.'
   ;

IDENTIFIER
   : [A-Za-z0-9.]+
   ;

WS
   : [ \t\r\n]+ -> skip
   ;
