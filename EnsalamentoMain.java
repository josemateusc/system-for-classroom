
public class EnsalamentoMain {
	public static void main(String[] args) {
		Ensalamento e = new Ensalamento();
		Sala s1 = new Sala(1000, 101, 70, true);
		Sala s2 = new Sala(2000, 201, 100, false);
		Sala s3 = new Sala(3000, 301, 50, true);
		e.addSala(s1);
		e.addSala(s2);
		e.addSala(s3);
		
		Turma t1 = new Turma("Turma 1", "Prof. 1", 60, true);
		t1.addHorario(2);
		t1.addHorario(16);
		t1.addHorario(30);
		e.addTurma(t1);
		e.alocarTodas();
		System.out.println(e.relatorioTurmasPorSala());
		
	}
}
