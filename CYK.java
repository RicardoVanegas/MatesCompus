
public class CYK {

	private String cadena="";
	private String [] reglas;
	private char [][] terminales;
	private char [][] tabla;
	private String [][] generados;
	
	private CYK() {
		this.cadena="aabbab";
		this.reglas= new String[5];
		this.reglas[0]= "S AB SS AC BD BA";
		this.reglas[1]= "A a";
		this.reglas[2]= "B b";
		this.reglas[3]= "C SB";
		this.reglas[4]= "D SA";
		this.tabla = new char[this.cadena.length()][this.cadena.length()];
		for(int i=0; i<this.tabla.length;i++) {
			for(int j=0; j<this.tabla.length;j++) {
				this.tabla[i][j]='0';
			}
		}
		this.generados = new String[7][2];
		this.terminales = new char[2][2];
		this.terminales[0][0]= 'a';
		this.terminales[0][1]= 'A';
		this.terminales[1][0]= 'b';
		this.terminales[1][1]= 'B';
		this.generados[0][0]="AB";
		this.generados[1][0]="SS";
		this.generados[2][0]="AC";
		this.generados[3][0]="BD";
		this.generados[4][0]="BA";
		this.generados[5][0]="SB";
		this.generados[6][0]="SA";
		this.generados[0][1]="S";
		this.generados[1][1]="S";
		this.generados[2][1]="S";
		this.generados[3][1]="S";
		this.generados[4][1]="S";
		this.generados[5][1]="C";
		this.generados[6][1]="D";
	}
	
	
	private void resuelveTabla() {
		
		for(int i = 0; i < this.cadena.length(); i++) {
			diagonal(i);
		}
		if(this.tabla[this.cadena.length()-1][0]==this.reglas[0].charAt(0)) {
			System.out.println("la cadena si pertenece");
			imprimeTabla();
			System.out.println();
			dibujaArbol();
		}
		
		
		
	}
	private void dibujaArbol() {
		System.out.println("              "+this.tabla[this.tabla.length-1][0]);
		System.out.println("     "+this.tabla[this.tabla.length-3][this.tabla.length-6]+"                   "+this.tabla[this.tabla.length-1][this.tabla.length-2]);
		System.out.println(""+this.tabla[this.tabla.length-6][this.tabla.length-6] +"          "+this.tabla[this.tabla.length-3][this.tabla.length-5] +"          "+this.tabla[this.tabla.length-2][this.tabla.length-2]+"      " +this.tabla[this.tabla.length-1][this.tabla.length-1]);
		System.out.println("a       "+this.tabla[this.tabla.length-4][this.tabla.length-5] +"     "+this.tabla[this.tabla.length-3][this.tabla.length-3]+"       a      b");
		System.out.println("     "+this.tabla[this.tabla.length-5][this.tabla.length-5]+"     "+this.tabla[this.tabla.length-4][this.tabla.length-4]+"  b");
		System.out.println("     a     b");
		
	}
	private void diagonal(int x) {
		if(x == 0) {
			for(int i = 0; i<this.cadena.length(); i++) {
				if(this.cadena.charAt(i) == this.terminales[0][0]) {
					this.tabla[i][i]=this.terminales[0][1];
					
				}else if(this.cadena.charAt(i) == this.terminales[1][0]) {
					this.tabla[i][i]=this.terminales[1][1];
				}else {
					System.out.println("no hay");
				}
			}
			
		}else if(x == 1){			
			for(int i =x; i<this.cadena.length();i++) {
				String e = ""+this.tabla[i-1][i-1]+this.tabla[i][i];
				for(int j=0; j<this.generados.length;j++) {
					if(e.equals(this.generados[j][0])) {
						this.tabla[i][i-1]=this.generados[j][1].charAt(0);						
					}					
				}	
			}
		}else if(x==2) {
			for(int i =x; i<this.cadena.length();i++) {
					String e = ""+this.tabla[i-1][i-2]+this.tabla[i][i];
					String d = ""+this.tabla[i-2][i-2]+this.tabla[i][i-1];
					for(int j=0; j<this.generados.length;j++) {
						if(e.equals(this.generados[j][0])) {
							this.tabla[i][i-2]=this.generados[j][1].charAt(0);							
						}						
					}
					for(int j=0; j<this.generados.length;j++) {
						if(d.equals(this.generados[j][0])) {
							this.tabla[i][i-2]=this.generados[j][1].charAt(0);						
						}
					}				
			}
		}else if(x==3) {
			for(int i=x; i<this.cadena.length();i++) {
				String e = ""+this.tabla[i-3][i-3]+this.tabla[i][i-2];
				String d = ""+this.tabla[i-2][i-3]+this.tabla[i][i-1];
				String f = ""+this.tabla[i-1][i-3]+this.tabla[i][i];
				for(int j=0; j<this.generados.length;j++) {
					if(e.equals(this.generados[j][0])) {
						this.tabla[i][i-3]=this.generados[j][1].charAt(0);
					}
				}
				for(int j=0; j<this.generados.length;j++) {
					if(d.equals(this.generados[j][0])) {
						this.tabla[i][i-3]=this.generados[j][1].charAt(0);
					}
				}
				for(int j=0; j<this.generados.length;j++) {
					if(f.equals(this.generados[j][0])) {
						this.tabla[i][i-3]=this.generados[j][1].charAt(0);
					}
				}
			}
		}else if(x==4) {
			for(int i=x; i<this.cadena.length();i++) {
				String e = ""+this.tabla[i-1][i-4]+this.tabla[i][i];
				String d = ""+this.tabla[i-2][i-4]+this.tabla[i][i-1];
				String f = ""+this.tabla[i-3][i-4]+this.tabla[i][i-2];
				String g = ""+this.tabla[i][i-3]+this.tabla[i-4][i-4];
				for(int j=0; j<this.generados.length;j++) {
					if(e.equals(this.generados[j][0])) {
						this.tabla[i][i-4]=this.generados[j][1].charAt(0);
					}
				}
				for(int j=0; j<this.generados.length;j++) {
					if(d.equals(this.generados[j][0])) {
						this.tabla[i][i-4]=this.generados[j][1].charAt(0);
					}
				}
				for(int j=0; j<this.generados.length;j++) {
					if(f.equals(this.generados[j][0])) {
						this.tabla[i][i-4]=this.generados[j][1].charAt(0);
					}
				}
				for(int j=0; j<this.generados.length;j++) {
					if(g.equals(this.generados[j][0])) {
						this.tabla[i][i-4]=this.generados[j][1].charAt(0);
					}
				}
			}
		}else if(x==5) {
			for(int i=x; i<this.cadena.length();i++) {
				String e = ""+this.tabla[i-5][i-5]+this.tabla[i][i-4];
				String d = ""+this.tabla[i-4][i-5]+this.tabla[i][i-3];
				String f = ""+this.tabla[i-3][i-5]+this.tabla[i][i-2];
				String g = ""+this.tabla[i-2][i-5]+this.tabla[i][i-1];
				String h = ""+this.tabla[i-1][i-5]+this.tabla[i][i];
				for(int j=0; j<this.generados.length;j++) {
					if(e.equals(this.generados[j][0])) {
						this.tabla[i][i-5]=this.generados[j][1].charAt(0);
					}
				}
				for(int j=0; j<this.generados.length;j++) {
					if(d.equals(this.generados[j][0])) {
						this.tabla[i][i-5]=this.generados[j][1].charAt(0);
					}
				}
				for(int j=0; j<this.generados.length;j++) {
					if(f.equals(this.generados[j][0])) {
						this.tabla[i][i-5]=this.generados[j][1].charAt(0);
					}
				}
				for(int j=0; j<this.generados.length;j++) {
					if(g.equals(this.generados[j][0])) {
						this.tabla[i][i-5]=this.generados[j][1].charAt(0);
					}
				}
				for(int j=0; j<this.generados.length;j++) {
					if(h.equals(this.generados[j][0])) {
						this.tabla[i][i-5]=this.generados[j][1].charAt(0);
					}
				}
			}
		}
		
		
	}
	public void imprimeTabla() {
		for(int i=0; i<this.tabla.length;i++) {
			for(int j=0; j<this.tabla.length;j++) {
				if(j<=i) {
					System.out.print(this.tabla[i][j] + ", ");
				}
			}
			System.out.println();
		}
	}
	
	public static void main(String args[]) {
		
		CYK a = new CYK();
		a.resuelveTabla();
		
		
		
		
		
	}
}
