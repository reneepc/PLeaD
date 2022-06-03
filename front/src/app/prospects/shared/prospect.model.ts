interface Prospect {
    id: number;
    nomeRazaoSocial: string;
    rendaAnual: number;
    expostaPoliticamente: boolean;     
}

export interface ProspectPF extends Prospect {
    sobrenome: string;
    cpf: string;
    nomePublico: string;
}

export interface ProspectPJ extends Prospect {
    cnpj: string;
    nomeFantasia: string;

}