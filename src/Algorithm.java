import java.io.*;
import java.util.ArrayList;
public class Algorithm {
	public static void main(String[] args)
	{
		int score=0;
		//String fname = "c_no_hurry";
		//String fname = "d_metropolis";
		//String fname = "b_should_be_easy";
		String fname = "e_high_bonus";
		try {
			FileReader fr = new FileReader(fname+".in");
			BufferedReader br = new BufferedReader(fr);
			String[] tok = br.readLine().split(" ");
			int R = Integer.parseInt(tok[0]);
			int C = Integer.parseInt(tok[1]);
			int F = Integer.parseInt(tok[2]);
			int N = Integer.parseInt(tok[3]);
			int B = Integer.parseInt(tok[4]);//bonus if start on time
			int T = Integer.parseInt(tok[5]);
			System.out.println(F+" vehicles " + "Bonus " + B + " " + R+"x"+C);
			Ride[] rides = new Ride[N];
			Vehicle[] v = new Vehicle[F];
			for(int i=0;i<N;i++)
			{
				tok = br.readLine().split(" ");
				rides[i] = new Ride(i,Integer.parseInt(tok[0]),Integer.parseInt(tok[1]),Integer.parseInt(tok[2]),Integer.parseInt(tok[3]),Integer.parseInt(tok[4]),Integer.parseInt(tok[5]));
			}
			for(int i=0;i<F;i++)
			{
				v[i] = new Vehicle(i);
			}
			ArrayList<Ride> r = new ArrayList<Ride>();
			for(Ride rr:rides)
			{
				r.add(rr);
			}
			for(Vehicle vh:v)
			{
				int time = 0;
				while(time<=T)
				{
					ArrayList<Ride> tmp = new ArrayList<Ride>();
					for(Ride rr:r)
					{
						if(rr.f>=distS(vh.x,rr.a)+distS(vh.y,rr.b)+rr.pts)
						{
							rr.cost=calcCost(vh,rr,time);
							tmp.add(rr);
						}
					}
					if(tmp.isEmpty()==false)
					{
						Ride []arr = tmp.toArray(new Ride[tmp.size()]);
						quickSort(arr,0,arr.length-1);
						Ride next = arr[0];
						vh.x=next.x;
						vh.y=next.y;
						time += next.cost;
						vh.rds.add(next);
						r.remove(next);
						continue;
					}else
					{
						break;
					}
				}
			}
			//out and score
			FileWriter fw = new FileWriter(new File(fname+".out"));
			BufferedWriter bw = new BufferedWriter(fw);
			for(int i=0;i<F;i++)
			{
				bw.write(v[i].rds.size()+" ");
				int t=0,x=0,y=0;
				for(Ride p:v[i].rds)
				{
					int arrival = t+distS(x,p.a)+distS(y,p.b);
					int stat=0;
					int wait = arrival>p.s?0:p.s-arrival;
					if(wait==0&&arrival<=p.s)
						stat=1;
					t = wait + arrival;
					x=p.x;y=p.y;
					t=t+p.pts;
					if(wait>0 || stat==1 )
					{
						score = score + B + distS(p.a,p.x) + distS(p.b,p.y);
					}else if(arrival+p.pts<=p.f)
					{
						score+=distS(p.a,p.x) + distS(p.b,p.y);
					}
					bw.write(p.i+" ");
				}
				bw.newLine();
			}
			bw.close();
		}catch(Exception e){System.out.println(e.toString());}
		System.out.println("END score for "+fname+".in is >>>>  " + score);
	}
	static int partition(Ride arr[], int left, int right) //change
	{
	      int i = left, j = right;
	      Ride tmp; //change
	      Ride pivot = arr[(left + right) / 2];  //comparator***
	     
	      while (i <= j) {
	            while (arr[i].cost < pivot.cost) //comparator***
	                  i++;
	            while (arr[j].cost > pivot.cost)   //comparator***
	                  j--;
	            if (i <= j) {
	                  tmp = arr[i];
	                  arr[i] = arr[j];
	                  arr[j] = tmp;
	                  i++;
	                  j--;
	            }
	      };
	      return i;
	}
	static void quickSort(Ride arr[], int left, int right) { //change
	      int index = partition(arr, left, right);
	      if (left < index - 1)
	            quickSort(arr, left, index - 1);
	      if (index < right)
	            quickSort(arr, index, right);
	}
	public static int distS(int w,int z)
	{
		if(w>z)
			return w-z;
		else
			return z-w;
	}
	public static int calcCost(Vehicle v,Ride r,int i)
	{
		int cost=0;
		int wait = distS(r.s,i+distS(v.x,r.a)+distS(v.y,r.b));
		cost = distS(v.x,r.a)+distS(v.y,r.b)+wait+r.pts;
		return cost;
	}

}
