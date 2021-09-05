export class ProfilStatistique {
    public id: number;

    public dateStatistique: Date;

    public nombreFollowersYoutube: number;

    public nombreVuesYoutube: number;

    public nombreFollowersFacebook: number;

    public nombreFollowersTwitter: number;

    public nombreFollowersInstagram: number;

    public profilId: number;


  constructor(
        id: number, 
        dateStatistique: Date, 
        nombreFollowersYoutube: number, 
        nombreVuesYoutube: number,
        nombreFollowersFacebook: number, 
        nombreFollowersTwitter: number, 
        nombreFollowersInstagram: number, 
        profilId: number
    ) {
        this.id = id;
        this.dateStatistique = dateStatistique;
        this.nombreFollowersYoutube = nombreFollowersYoutube;
        this.nombreVuesYoutube = nombreVuesYoutube;
        this.nombreFollowersFacebook = nombreFollowersFacebook;
        this.nombreFollowersTwitter = nombreFollowersTwitter;
        this.nombreFollowersInstagram = nombreFollowersInstagram;
        this.profilId = profilId;
    }

}