{### PROGRAM}
{
ID: ...
PROG: milk
LANG: PASCAL
}
uses
  sysutils;




var
   f,f2 : Text;
   wanted : longint;
   numberOfFarmers : integer;
   i,counter : integer;
   a : array[1..5000,1..2] of longint;
    c : longint;


   function getPrice(p : longint):longint;
   var
     r,c : longint;
   begin
     r := 0;
     c :=1;
     while (p>0) do
     begin { while }
       if (p>=a[c,2]) then
          r := r + a[c,2]*a[c,1]
       else
         r := r + p*a[c,1];

       	  
	  p := p - a[c,2];
	  c := c + 1;
   end;  { while }
     getPrice := r;
   end;

  procedure sort(counter : integer);
  var
    i,j,k : integer;
  begin

     for i  := 1 to counter - 1  do
       for j  := i + 1  to counter  do
           if (a[j,1]<a[i,1]) then
	   begin
	      k := a[i,1];
	      a[i,1] := a[j,1];
	      a[j,1] := k;
	      k := a[i,2];
	      a[i,2] := a[j,2];
	      a[j,2] := k;
	   end;

     
  end;

begin
   Assign(f,'milk.in');
   Reset(f);
   counter := 0;
   read(f,wanted);
   read(f,numberOfFarmers);
   for i := 1 to numberOfFarmers  do
   begin { for }
       read(f,a[i,1]);
       read(f,a[i,2]);
       counter := counter + 1;
   end;  { for }
   Close(f);
   sort(counter);

   c := getPrice(wanted);
   Assign(f2,'milk.out');
   Rewrite(f2);
   writeln(f2,c);
   Close(f2);
end.


{### END}

