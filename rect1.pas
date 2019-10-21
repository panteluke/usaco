{### PROGRAM}
{
ID: ...
LANG: PASCAL
PROG: rect1
}
uses
  sysutils;


type
  TList = record
    llx,lly,urx,ury,col : integer;
    next : ^TList;
 end;

var
   f : Text;
   k,l,n,i,llx,lly,urx,ury,c : integer;
   z,t,ib : ^TList;
   head : ^Tlist;
   node : ^TList;
   color : array[1..2500] of longint;
    nod : ^TList;
  start,addList : ^Tlist;

function min(a,b : integer) : integer;
begin
 min := a;
 if (b<a) then
   min := b;
end;

function max(a,b : integer) : integer;
begin
 max := a;
 if (b>a) then
   max := b;
end;


procedure intersect(llx,lly,urx,ury : integer);
begin
  new(nod);
  new (start);
  start := head;
   nod := start;
while not(nod^.next = z) do

begin { while }


  nod := start^.next;
  if ((llx>=nod^.urx) or (urx<=nod^.llx) or (lly>=nod^.ury) or (ury<=nod^.lly)) then
  begin
    start := nod;
    continue;
  end;
  if ((llx<=nod^.llx) and (urx>=nod^.urx) and (lly<=nod^.lly) and (ury>=nod^.ury)) then
  begin

     start^.next := nod^.next;
//     start := start^.next;
     continue;
  end;
  if ((urx<nod^.urx) and (ury<nod^.ury)) then
  begin
    new(addList);
    addList^.llx := urx;
    addList^.urx := nod^.urx;
    addList^.lly := ury;
    addList^.ury := nod^.ury;
    addList^.col := nod^.col;
    addList^.next := nod^.next;
    start^.next := addList;
    start := start^.next
  end;
  if ((urx<nod^.urx) and (lly>nod^.lly)) then
  begin
    new(addList);
    addList^.llx := urx;
    addList^.urx := nod^.urx;
    addList^.lly := nod^.lly;
    addList^.ury := lly;
    addList^.col := nod^.col;
    addList^.next := nod^.next;
        start^.next := addList;
    start := start^.next;
//    writeln(addList^.llx,addList^.lly,addList^.urx,addList^.ury,addList^.col);

//    nod^.next:= addList^.next;
  end;
  if ((llx>nod^.llx) and (ury<nod^.ury)) then
  begin
    new(addList);
    addList^.llx := nod^.llx;
    addList^.urx := llx;
    addList^.lly := ury;
    addList^.ury := nod^.ury;
    addList^.col := nod^.col;
    addList^.next := nod^.next;
        start^.next := addList;
    start := start^.next;
  //  writeln(addList^.llx,addList^.lly,addList^.urx,addList^.ury,addList^.col);

//    nod^.next:= addList^.next;
  end;
  if ((llx>nod^.llx) and (lly>nod^.lly)) then
  begin
    new(addList);
    addList^.llx := nod^.llx;
    addList^.urx := llx;
    addList^.lly := nod^.lly;
    addList^.ury := lly;
    addList^.col := nod^.col;
    addList^.next := nod^.next;
      start^.next := addList;
    start := start^.next;
  //  writeln(addList^.llx,addList^.lly,addList^.urx,addList^.ury,addList^.col);

//    nod^.next:= addList^.next;
  end;
  if ((urx<nod^.urx) and (urx>nod^.llx)) then
  begin
    new(addList);
    addList^.llx := urx;
    addList^.urx := nod^.urx;
    addList^.lly := max(lly,nod^.lly);
    addList^.ury := min(ury,nod^.ury);
    addList^.col := nod^.col;
    addList^.next := nod^.next;
      start^.next := addList;
    start := start^.next;
  //  writeln(addList^.llx,addList^.lly,addList^.urx,addList^.ury,addList^.col);

//    nod^.next:= addList^.next;
  end;
if ((llx>nod^.llx) and (llx<nod^.urx)) then
  begin
    new(addList);
    addList^.llx := nod^.llx;
    addList^.urx := llx;
    addList^.lly := max(lly,nod^.lly);
    addList^.ury := min(ury,nod^.ury);
    addList^.col := nod^.col;
    addList^.next := nod^.next;
        start^.next := addList;
    start := start^.next;
 //   writeln(addList^.llx,addList^.lly,addList^.urx,addList^.ury,addList^.col);

  //  nod^.next:= addList^.next;

  end;
if ((ury<nod^.ury) and (ury>nod^.lly)) then
  begin
    new(addList);
    addList^.llx := max(llx,nod^.llx);
    addList^.urx := min(urx,nod^.urx);
    addList^.lly := ury;
    addList^.ury := nod^.ury;
    addList^.col := nod^.col;
    addList^.next := nod^.next;
    start^.next := addList;
    start := start^.next;
 //   writeln(addList^.llx,addList^.lly,addList^.urx,addList^.ury,addList^.col);

    //nod^.next:= addList^.next;

  end;
if ((lly>nod^.lly) and (lly<nod^.ury)) then
  begin
    new(addList);
    addList^.llx := max(llx,nod^.llx);
    addList^.urx := min(urx,nod^.urx);
    addList^.lly := nod^.lly;
    addList^.ury := lly;
    addList^.col := nod^.col;
    addList^.next := nod^.next;
    start^.next := addList;
    start := start^.next;
//    nod^.next:= addList^.next;
 //   writeln(addList^.llx,addList^.lly,addList^.urx,addList^.ury,addList^.col);

  end;
//  start^.next := nod^.next;

end;  { while }

end;



begin
   fillchar(color,sizeof(color),0);
   Assign(f,'rect1.in');
   Reset(f);
   read(f,k);
   read(f,l);
   readln(f,n);
   new(head);
   new(z);

   head^.next := z;
   z^.next := z;
   new(node);
   node^.col := 1;
   node^.llx := 0;
   node^.lly := 0;
   node^.urx := k;
   node^.ury := l;
   node^.next := head^.next;
   head^.next := node;
   for i := 1 to n  do
   begin
     read(f,llx);
     read(f,lly);
     read(f,urx);
     read(f,ury);
     readln(f,c);
     intersect(llx,lly,urx,ury);
     new(node);
     node^.llx := llx;
     node^.lly := lly;
     node^.urx := urx;
     node^.ury := ury;
     node^.col := c;
     node^.next := head^.next;
     head^.next := node;
   end;
   Close(f);




   while not(head^.next = z) do
   begin { while }
     head := head^.next;
     color[head^.col] := color[head^.col] + (head^.urx-head^.llx)*(head^.ury - head^.lly);
   end;  { while }



   Assign(f,'rect1.out');
   Rewrite(f);
   for i := 1 to 2500  do
      if (color[i]<>0) then
      begin
         write(f,i);
	 write(f,' ');
	 writeln(f,color[i]);

      end;
   Close(f);

end.


{### END}
