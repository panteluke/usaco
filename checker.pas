{### PROGRAM}
{
ID: ...
LANG: PASCAL
PROG: checker
}
uses
  sysutils;


var
   f,f2 : Text;
   row,colu : array[1..13] of byte;
   udiag,ddiag : array[1..26] of byte;
   n,times: byte;
   solutions:longint;





procedure toFile;
var
  k,j : byte;
begin
   Assign(f2,'checker.out');
   if (times=0) then
      Rewrite(f2)
   else
      Append(f2);
   for k := 1 to n-1  do
   begin
        write(f2,colu[k]);
        write(f2,' ');
   end;  { for }
   writeln(f2,colu[n]);
   Close(f2);
end;

procedure solve(col : byte);
var
  i,j,k: byte;


begin
  if (col = n + 1) then
  begin
    if (times<3) then
    begin
       toFile;
       times := times + 1;
    end;
       solutions := solutions + 1
  end
  else
    begin
      for i := 1  to n  do
      begin { for }
        if ((row[i]=0) and (udiag[i+col]=0) and (ddiag[i-col+n]=0)) then
	begin

	  colu[col] := i;
	  row[i] := row[i] + col ;

	  udiag[i+col] := udiag[i+col] + 1;
 	  ddiag[i-col+n] := ddiag[i-col+n] + 1;

	  solve(col+1);

	  row[i] := row[i] - col;
	  udiag[i+col] := udiag[i+col] - 1;
 	  ddiag[i-col+n] := ddiag[i-col+n] - 1;

        end;
      end;  { for }

    end;

end;

begin
   Assign(f,'checker.in');
   Reset(f);
   readln(f,n);
   Close(f);
   solutions := 0;
   times := 0;
   solve(1);
   Assign(f2,'checker.out');
   Append(f2);
   writeln(f2,solutions);
   Close(f2);
end.


{### END}

