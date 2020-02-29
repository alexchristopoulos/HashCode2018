
public class Ride {
	int i,a,b,x,y,s,f;
	int time_assigned=-1;
	float logos;int pts=0;
	int flag;
	int wait=0;
	int cost=0;
	public Ride(int id,int sr,int sc,int er,int ec,int es,int ef)
	{
		i=id;
		a=sr;
		b=sc;
		x=er;
		y=ec;
		s=es;
		f=ef;
		pts = abs(a,x) + abs(b,y);		
	}
	public static int abs(int k,int l)
	{
		if(k>l)
			return k-l;
		else 
			return l-k;
	}
	public int distFromS(int vx,int vy)
	{
		return abs(vx,this.a)+abs(vy,this.y);
	}
	public void setwaitTime(int arrt)
	{
		wait = this.s>=arrt?this.s-arrt+1:1;
	}
}
