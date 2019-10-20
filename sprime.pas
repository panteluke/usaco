{### PROGRAM}
{
ID: ...
LANG: PASCAL
PROG: sprime
}
uses
  sysutils;


var
   f,f2 : Text;
   n : byte;   
   s : longint;


function isPrime(k:longint):boolean;
var
 i : integer;
 p : longint;
begin
if ( (k=2) or (k=3) or (k=5) or (k=7)) then
begin
  isPrime := true;
  exit;
end;
if ((k mod 2=0) or (k=1)) then
    begin
      isPrime := false;
      exit;
    end;

 for i := 3 to round(sqrt(k)) + 1 do
    if (k mod i = 0) then
    begin
      isPrime := false;
      exit;
    end;
 isPrime := true;     
end;

procedure solve(k : byte);
var 
 i : byte;
begin
if (k=0) then
begin
   Assign(f2,'sprime.out');
   Append(f2);
   writeln(f2,s);
   Close(f2);
end
else
begin
   for i := 1  to 9 do
   begin { for }
     s := s*10 + i;
     if (isPrime(s)) then
        solve(k-1);
     s := (s-i) div 10;
   end;  { for }
   
end;

end;

begin
   Assign(f,'sprime.in');
   Reset(f);
   readln(f,n);
   Close(f);
   Assign(f,'sprime.out');
   Rewrite(f);
   Close(f);
   s := 0;
   solve(n);

end.


{### END}

