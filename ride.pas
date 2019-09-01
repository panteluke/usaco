{
ID: ...
PROG: ride
LANG: PASCAL
}


uses crt;


var
  f1,f2: text;
  s : string;
  i,k,j :integer;

function hash(s : char):integer;

begin
 hash := ord(s) - 64;

end;

begin
  clrscr;
  Assign(f1,'ride.in');
  reset(f1);
  readln(f1,s);
  k:=1;
  for i:= 1 to length(s) do
    k := (k*hash(s[i])) mod 47;
  j:= 1;
  readln(f1,s);
  for i:= 1 to length(s) do
    j := (j*hash(s[i])) mod 47;
  Assign(f2,'ride.out');
  Rewrite(f2);
  if k = j then
    writeln(f2,'GO')
  else
    writeln(f2,'STAY');
  Close(f1);
  Close(f2);

end.


