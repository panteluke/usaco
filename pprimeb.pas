{### PROGRAM}
{
ID: ...
LANG: PASCAL
PROG: pprime
}
uses
  sysutils;


var
  a : array[1..16000] of longint;
  f : Text;
  i : longint;
  j,k,l : longint;
  fl,fl2 : boolean;
  min,max : longint;
  s : string;
  add: integer;

begin
   Assign(f,'pprime.in');
   Reset(f);
   Readln(f,s);
   Close(f);
   for i := 1 to length(s) do
    if s[i] = ' ' then
    begin
      min := strtoint(copy(s,1,i-1));
      max := strtoint(copy(s,i+1,length(s)-i));
      break;
    end;
 
  a[1] := 3;
  a[2] := 5;
  a[3] := 7;
  a[4] := 11;
  j := 5;
  i := 13;
  fl := true;
  while i < 10001 do
  begin
     a[j] := i;
     for k := 1 to j-1 do
     begin
       if a[k] > sqrt(i) then
         break;
       if (i mod a[k] =0 ) then
       begin
         fl := false;
         break;
       end;
     end;
     if fl then
     begin
       inc(j);
       
     end
     else
        fl := true;

     i := i + 2;
  end;
  i := min;
  if i mod 2=0 then inc(i);
  fl2 := true;
  fl := true;
  //j := 5;
   Assign(f,'pprime.out');
  Rewrite(f);
  if min = 5 then
     writeln(f,i);
  while  i < max+1 do
  begin
    add := 2;
    s := inttostr(i);
    fl := true;
    fl2 := true;
   // a[j] := i;
    if s[1] in ['1','3','7','9'] then
      for k := 1 to length(s) div 2 do
      begin
        if s[k] <> s[length(s) - k + 1] then
        begin
          fl2 := false;
          break;
        end;
     end
    else
     fl2 := false;
    if fl2 then
    begin
       if (Length(s) > 2) then begin
         if not(s[Length(s) div 2] = '9') then begin
           add := 1;
           for l := 1 to Length(s) div 2 do
             add := add * 10; 
         end;
       end;
       
       for k :=1 to j - 1 do begin
          if ((i mod a[k] = 0) and (i<>a[k])) then
          begin
            fl := false;
            break;
          end;
           if a[k] * a[k] > i then
             break;
       end;
       if fl then
       begin
         writeln(f,i);
        // inc(j);
       end;

    end;
  
     i := i + add;
     if i > 9999999 then
        break;
  end;


  
   
   
   Close(f);


end.


{### END}
