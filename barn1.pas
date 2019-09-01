{
ID: ...
PROG: barn1
LANG: PASCAL
}
uses
  sysutils;




var
   f,f2 : Text;
   m,s,c,counter,i,j : byte;
   a : array[1..200] of byte;
   str3,sub : string;

function fillArray(var k : byte):boolean;
 var
   str1,s2 :string; 
   ak : byte;
begin
str1 :='';

  for i  :=1 to s do
  begin { for }
    str(a[i],s2);
    str1 := str1 + s2;
  end;  { for }
  sub :='1';
  for i :=1  to k  do
  begin { for }
    sub := sub +'0';   
  end;  { for }
  sub := sub + '1';
  ak := pos(sub,str1);
  if (ak>0) then
  begin
   for i := 1 to k  do
   begin { for }
    a[ak+i] := 1;
   end;  { for }
   fillArray := true;
  end
 else
   begin
    k := k + 1;
    fillArray := false;
    end;
end;

begin
   Assign(f,'barn1.in');
   Reset(f);
   counter := 0;
   read(f,m);
   read(f,s);
   readln(f,c);
   for i :=1  to 200  do
   begin { for }
      a[i] := 0;
   end;  { for }
   
   for i := 1 to c  do
   begin { for }
       readln(f,str3);
       val(str3,j);
       a[j] := 1;
       counter := counter + 1;
   end;  { for }

   Close(f);
   
   for i := 1  to s -1  do
     if ((a[i]=1) and (a[i+1]=1)) then
     begin
         counter := counter - 1;
	
     end;
   j := 1;
   while (counter>m) do
   begin { while }
     if (fillArray(j)) then
       counter := counter - 1;
   end;  { while }
   counter := 0;
   for i := 1  to s  do
   begin
     // writeln(a[i]);
      if (a[i]=1) then
         counter := counter + 1;
   end;

   Assign(f2,'barn1.out');
   Rewrite(f2);
   writeln(f2,counter);
   Close(f2);
end.


{### END}

