{
ID: ...
PROG: friday
LANG: PASCAL
}
Program Test;
Var fin, fout: text;
    a: word;
    days: array[0..6] of integer;
    i: integer;
    startDay: integer;
function IsLeapYear(year: integer): boolean;
begin
  IsLeapYear := ( ( (year mod 4 = 0) and (year mod 100 <> 0) ) or (year mod 400 = 0) );
end;
function calcNextDay(year, day: integer): integer;
begin
  if IsLeapYear(year) then calcNextDay := (day + 2) mod 7 else calcNextDay := (day + 1) mod 7;
end;
procedure calc(year, day: integer);
begin
  if IsLeapYear(year) then begin
    days[day] := days[day] + 1;
    days[(day + 1) mod 7] := days[(day + 1) mod 7] + 2;
    days[(day + 2) mod 7] := days[(day + 2) mod 7] + 2;
    days[(day + 3) mod 7] := days[(day + 3) mod 7] + 1;
    days[(day + 4) mod 7] := days[(day + 4) mod 7] + 2;
    days[(day + 5) mod 7] := days[(day + 5) mod 7] + 3;
    days[(day + 6) mod 7] := days[(day + 6) mod 7] + 1;
  end
  else begin
    days[day] := days[day] + 1;
    days[(day + 1) mod 7] := days[(day + 1) mod 7] + 3;
    days[(day + 2) mod 7] := days[(day + 2) mod 7] + 1;
    days[(day + 3) mod 7] := days[(day + 3) mod 7] + 2;
    days[(day + 4) mod 7] := days[(day + 4) mod 7] + 2;
    days[(day + 5) mod 7] := days[(day + 5) mod 7] + 2;
    days[(day + 6) mod 7] := days[(day + 6) mod 7] + 1;
  end;
end;
Begin
    Assign(fin, 'friday.in'); Reset(fin);
    Assign(fout, 'friday.out'); Rewrite(fout);
    Readln(fin, a);
    for i := 0 to 6 do days[i] := 0; 	 
    startDay := 2;
    
    for i := 1900 to 1900 + a - 1 do begin
	calc(i, startDay);
	startDay := calcNextDay(i, startDay); 
    end;
    for i := 0 to 5 do begin
      Write(fout, days[i]);
      Write(fout, ' ');
    end;	
    writeln(fout, days[6]);
    Close(fout);
End.
