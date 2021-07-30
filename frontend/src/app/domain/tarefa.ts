import { anexo } from './anexo';
export class Tarefa {
    id: number;
    nome: string;
    dataInicio: Date;
    dataConclusao: Date;
    status: String;
    responsavelId: number;
    anexos: anexo[];
}