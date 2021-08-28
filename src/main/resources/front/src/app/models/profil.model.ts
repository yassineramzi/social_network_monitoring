export class Profil {
    public id: number;
    public nom: string;
    public lienYoutube: string;
    public lienTwitter: string;
    public lienFacebook: string;
    public lienInstagram: string;
    public dossierId: number;

    constructor(
        nom: string, 
        lienYoutube: string, 
        lienTwitter: string, 
        lienFacebook: string, 
        lienInstagram: string,
        dossierId: number
    ) {
        this.nom = nom;
        this.lienYoutube = lienYoutube;
        this.lienTwitter = lienTwitter;
        this.lienFacebook = lienFacebook;
        this.lienInstagram = lienInstagram;
        this.dossierId = dossierId;
    }
}