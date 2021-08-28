import { ECategorie } from './enum/ecategorie.enum';
import { Profil } from './profil.model';

export class DossierSocial {
    public id: number;
    public nom: string;
    public categorie: ECategorie;
    public profils: Array<Profil> = new Array<Profil>(0);

    constructor(
        nom: string, 
        categorie: ECategorie
    ){
        this.nom = nom;
        this.categorie = categorie;
    }
}