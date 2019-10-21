{### PROGRAM}
{
ID: ...
LANG: PASCAL
PROG: clock
}
uses
  sysutils;



const

   a : array[1..19] of string = ('one','two','three','four','five','six','seven','eight','nine','ten','eleven'
   ,'twelve','thirteen','fourteen','fifteen','sixteen','seventeen','eighteen','nineteen');
   b : array[1..4] of string = ('twenty','thirty','forty','fifty');

var
   f,f2 : Text;
   input : string;
   hours,minutes : byte;
   result : string;

function capital(s : string):string;
var
  s2 : string;
  c : char;
begin
  s2 := '';
  c := s[1];
  s2 :=  s2+ chr(ord(c)-32);
  s2 := s2 + copy(s,2,20);
  capital := s2;
end;

begin
   Assign(f,'clock.in');
   Reset(f);
   readln(f,input);
   Close(f);
   val(copy(input,1,pos(':',input)-1),hours);
   val(copy(input,pos(':',input)+1,2),minutes);
   writeln(hours);
   writeln(minutes);
   case minutes of

       0: result := capital(a[hours]) + ' o''clock';
  1..14,16..19 : result := capital(a[hours]) +' '+a[minutes];
     15: result := 'Quarter past ' + a[hours];
     20..44 :
       begin
        result := capital(a[hours]) +' '+b[minutes div 10 -1];
	if (minutes mod 10<>0) then
          result := result + '-'+a[minutes mod 10];

       end;
     45: if (hours<>12) then
          result := 'Quarter to ' + a[hours+1]
         else
	  result := 'Quarter to one';
     46..59 :
       begin
        result := capital(a[60-minutes])+' to ';
        if (hours<>12) then
           result := result + a[hours+1]
        else
	  result := result +'one';
       end;

   end;


   Assign(f2,'clock.out');
   Rewrite(f2);
   writeln(f2,result);
   Close(f2);
end.


{### END}

