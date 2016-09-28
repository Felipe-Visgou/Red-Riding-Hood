
public class Doce {
	public float fator;
	public String nome;
	public int qtd;
	public Doce(String name, float f){
		nome = name;
		fator = f;
		qtd = 5;
	}
	public void decCandy(int i)
	{
		if(i == 0){
			System.out.println("Nao ha mais doces desse tipo");
			return;
		}
		else{
			while(i > 0){
			qtd--;
			i--;
			}
		}
	}
}
