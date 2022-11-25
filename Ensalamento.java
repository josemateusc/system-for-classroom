import java.util.*;

public class Ensalamento {
	ArrayList<Sala> salas;
	ArrayList<Turma> turmas;
	ArrayList<TurmaEmSala> ensalamento;
	
	public Ensalamento() {
		salas = new ArrayList<Sala>();
		turmas = new ArrayList<Turma>();
		ensalamento = new ArrayList<TurmaEmSala>();
	}
	
	public void addSala(Sala sala) {
		salas.add(sala);
	}
	
	public void addTurma(Turma turma) {
		turmas.add(turma);
	}
	
	public Sala getSala(Turma turma){
		int tamEnsalamento = ensalamento.size();
		for(int i=0; i<tamEnsalamento; i++) {
			if(ensalamento.get(i).turma == turma) return ensalamento.get(i).sala;
		}
		return null;
	}
	
	public boolean salaDisponivel(Sala sala, int horario){
		int tamEnsalamento = ensalamento.size();
		for(int i=0; i<tamEnsalamento; i++) {
			if(ensalamento.get(i).sala == sala) {
				for(int j=0; j < ensalamento.get(i).turma.horarios.size();j++) {
					if(ensalamento.get(i).turma.horarios.get(j).equals(horario)) return false;
				}
			}
		}
		return true;
	}
	
	public boolean salaDisponivel(Sala sala, ArrayList<Integer> horarios) {
		int tamanho = horarios.size();
		for(int i=0; i<tamanho; i++) {
			if(!salaDisponivel(sala, horarios.get(i))) return false;
		}
		return true;
	}
	
	public boolean alocar(Turma turma, Sala sala){
		if(turma.acessivel == sala.acessivel || 
		   turma.acessivel == false && sala.acessivel == true) {
			if(sala.capacidade >= turma.numAlunos) {
				if(salaDisponivel(sala, turma.horarios)) {
					TurmaEmSala turmaEmSala = new TurmaEmSala(turma,sala);
					ensalamento.add(turmaEmSala);
					return true;
				}
			}
		}
		
		return false;
	}
	
	class SalaComparator implements Comparator<Sala>{
		public int compare(Sala sala1, Sala sala2) {
			if(sala1.capacidade == sala2.capacidade) return 0;
			if(sala1.capacidade > sala2.capacidade) return 1;
			else return -1;
		}
	}
	
	void alocarTodas() {
		ArrayList<Sala> tamSalas = new ArrayList<Sala>();
		for(int i=0; i<salas.size(); i++)
			tamSalas.add(salas.get(i));
			
		Collections.sort(tamSalas, new SalaComparator());
		
		for(int i=0;i<turmas.size();i++) {
			for(int j=0; j<salas.size();j++) {
				if(alocar(turmas.get(i), tamSalas.get(j))) break;
			}
		}
	}
	
	public int getTotalTurmasAlocadas() {
		int tamanhoEnsalamento = ensalamento.size();
		int total = 0;
		for(int i=0; i<tamanhoEnsalamento; i++) {
			if(ensalamento.get(i).sala != null) {
				total++;
			}
		}
		return total;
	}
	
	
	public int getTotalEspacoLivre() {
		int total=0;
		int tamanhoEnsalamento = ensalamento.size();
		for(int i=0;i<tamanhoEnsalamento;i++) {
			total += ensalamento.get(i).sala.capacidade - ensalamento.get(i).turma.numAlunos;
		}
		return total;
	}
	
	public String relatorioResumoEnsalamento() {
		int totalSala = salas.size();
		int totalTurma = turmas.size();
		int totalAlocada = ensalamento.size();
		return "Total de Salas: "+ totalSala +"\n"
				+ "Total de Turmas: "+ totalTurma +"\n"
				+ "Turmas Alocadas: "+ totalAlocada +"\n"
				+ "Espaços Livres: " + getTotalEspacoLivre();
	}
	
	public String relatorioTurmasPorSala() {
		String str = "";
		str += relatorioResumoEnsalamento() + "\n";
		for(int i=0;i<salas.size();i++) {
			str += "\n";
			str += "--- " + salas.get(i).getDescricao()+ " ---\n";
			for(int j=0; j<ensalamento.size(); j++) {
				if(ensalamento.get(j).sala == salas.get(i))
					str += "\n" + ensalamento.get(j).turma.getDescricao() + "\n";
			}
		}
		return str;
	}
	public String relatorioSalasPorTurma() {
		String str = relatorioResumoEnsalamento() + "\n";
		for(int i=0; i<turmas.size(); i++) {
			str += "\n";
			str += turmas.get(i).getDescricao()+"\n";
			Sala sl = getSala(turmas.get(i));
			if(sl == null) str += "Sala: SEM SALA\n";
			else str += "Sala: " + sl.getDescricao()+"\n"; 
			
		}
		return str;
	}
}
