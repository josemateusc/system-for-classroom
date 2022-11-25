import java.util.ArrayList;

public class Turma {
	String nome;
	String professor;
	int numAlunos;
	boolean acessivel;
	ArrayList<Integer> horarios;
	
	public Turma() {
		this("", "", 0,false);
	}
	
	public Turma(String nome, String professor, int numAlunos, boolean acessivel) {
		this.nome = nome;
		this.professor = professor;
		this.numAlunos = numAlunos;
		this.acessivel = acessivel;
		horarios = new ArrayList<Integer>();
	}
	
	public void addHorario(int horario) {
		horarios.add(horario);
	}
	
	public String getHorariosString() {
		String str = "";
		int tam = horarios.size();
		for(int i=0; i<tam; i++) {
			if(i>0) str += ", ";
			switch(horarios.get(i)/7) {
				case 0:
					str+="segunda ";
					break;
				case 1:
					if(horarios.get(i) == 7) {
						str += "segunda ";
					}
					else str+="terça ";
					break;
				case 2:
					if(horarios.get(i) == 14) {
						str += "terça ";
					}
					else str+="quarta ";
					break;
				case 3:
					if(horarios.get(i) == 21) {
						str += "quarta ";
					}
					else str+="quinta ";
					break;
				case 4:
					if(horarios.get(i) == 28) {
						str += "quinta ";
					}
					else str+="sexta ";
					break;
				case 5:
					str += "sexta ";
					break;
			}
			int horario = 8;
			for(int j=0, tempos = horarios.get(i)%7; j<tempos-1; j++) {
				if(tempos!=1) horario = horario + 2;
			}
			if(horarios.get(i)%7 == 0) str+="20hs";
			else str+= horario + "hs";
		}
		//Falta limpar
		return str;
	}
	
	public String getDescricao() {
		String acessibilidade;
		if(acessivel == true) acessibilidade = "sim";
		else acessibilidade = "não";
		
		return "Turma: " + nome + "\nProfessor: " + professor + 
				"\nNúmero de Alunos: " + numAlunos +
				"\nHorário: " +	getHorariosString() + "\nAcessível: " 
				+ acessibilidade;
	}
}
