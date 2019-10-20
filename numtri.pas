{### PROGRAM}
{
ID: ...
LANG: PASCAL
PROG: numtri
}
uses
  sysutils;


var
   f : Text;
   n : integer;   
   a : array[1..1000,1..1000] of longint;
   max : longint;
   i,j : integer;

procedure findMax;
var
 t : integer;
begin
  for t := 1 to n  do
    if (a[n,t]>max) then
       max := a[n,t];
end;

function maxim(p,r : longint):longint;
begin
  maxim := p;
  if (r>p) then
     maxim := r;
end;

procedure solve(k : integer);
var
  t : integer;
begin

if (k=n+1) then
begin
    findMax; 
end
else
begin
   a[k,1] := a[k-1,1] + a[k,1];
   a[k,k] := a[k-1,k-1] + a[k,k];
   for t := 2 to k-1  do
     a[k,t] := a[k,t] + maxim(a[k-1,t-1],a[k-1,t]);
   
   solve(k+1);
end;
 
end;

begin
  for i := 1  to 1000 do
    for j := 1  to 1000 do
       a[i,j] := 0;      
   Assign(f,'numtri.in');
   Reset(f);
   readln(f,n);
   for i := 1 to n  do
   begin
     for j := 1 to i do
        read(f,a[i,j]);
   end;     
   Close(f);
   max := 0;
   solve(2);
   Assign(f,'numtri.out');
   Rewrite(f);
   writeln(f,max);
   Close(f);

end.


{### END}

