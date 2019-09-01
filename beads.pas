{
ID: ...
PROG: beads
LANG: PASCAL
}
uses
  sysutils;


var
   b : array[0..350] of char;
   c : integer;
   f,f2 : Text;
   i : integer;
   j : integer;
   big : integer;
   cur : integer;
   fl,fl2 : boolean;
  
begin
   Assign(f,'beads.in');
   Reset(f);
   Readln(f,c);
   big := 0;
   fl2 := false;
   for i := 1 to c do
   begin
       read(f,b[i]);
   end;
   Close(f);
   writeln(c);
   for i := 1 to c do
     write(b[i]);

   writeln;
   for i:= 1 to c do
   begin
     cur := 0;
     write(i:2, ': ');
     write(b[i]);
     inc(cur);
     fl := false;
     if i <> c then
        j := 1
     else
        j := -c + 1;
     while ((b[i+j]=b[i]) or (b[i]='w') or (b[i+j]='w')) do
     begin
        write(b[i+j]);
        inc(cur);
        if b[i]='w' then
        begin
           b[i] := b[i+j];
           fl := true;
        end;
        j := j + 1;
        if i + j > c then
           j := - i + 1;
        if i + j = i then
	begin
	  fl2 := true;
	  break;
        end;
       
     end;
     if (fl) then
     begin
       b[i] := 'w';
       fl := false;
     end;
     writeln;
     b[0] := b[c];
     if i <> 1 then
        j := 1
     else
        j := -c + 1;
     fl := false;
     write(i:2, ': ');
     write(b[i-1]);
     inc(cur);
     while ((b[i-1]=b[i-j-1]) or (b[i-1]='w') or (b[i-j-1]='w')) do
     begin
        write(b[i-j-1]);
        inc(cur);
        if b[i-1]='w' then
        begin
           b[i-1] := b[i-j-1];
           fl := true;
        end;
        j := j + 1;
        if i - j=0 then
          j := i - c;
        if i - 1 = i - j -1 then
	begin
	  fl2 := true;
	  break;
        end;


     end;
     
     if (fl) then
     begin
       b[i-1]:='w';
       fl := false;
     end;
     writeln;
     if cur > big then
        big := cur;

   end;
   if ((fl2) or (big > c)) then
      big := c;
   Assign(f2,'beads.out');
   Rewrite(f2);
   writeln(f2,big);
   Close(f2);
end.


{### END}
