package beans;

/*Bean é uma classe java para processar dados em memória, não persistente*/
public class BeanCursoJSP {

    private String nome;
    private int ano;
    private char sexo;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public int calcula(int numero){
        return numero * 100;
    }
}
