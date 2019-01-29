package beans;

public enum TipoTelefone {

    CASA ("Casa"), CELULAR ("Celular"), TRABALHO ("Trabalho");

    private String descricao;

    TipoTelefone(String descricao) {
        this.descricao = descricao;
    }

    private TipoTelefone() {
    }

    public String getDescricao() {
        return descricao;
    }

    // Retornar enum de acordo com a descrição
    public static TipoTelefone valor(String desc) {

        switch(desc){
            case "Casa": return TipoTelefone.CASA;
            case "Celular": return TipoTelefone.CELULAR;
            case "Trabalho" : return TipoTelefone.TRABALHO;
            default: return null;
        }
    }

    public static TipoTelefone valor(Long valor){

        if (valor.equals(0L)) {
            return TipoTelefone.CASA;
        } else if (valor.equals(1L)) {
            return TipoTelefone.CELULAR;
        } else if (valor.equals(2L)) {
            return TipoTelefone.TRABALHO;
        } else {
            return null;
        }
    }

}
