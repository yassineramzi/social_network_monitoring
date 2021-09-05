import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { ProfilStatistique } from '@models/profilStatistique.model';
import { environment } from "@environments/environment";

type EntityArrayResponseType = HttpResponse<ProfilStatistique[]>;

@Injectable({
    providedIn: 'root'
})
export class ProfilStatistiqueService {

    public resourceUrl = environment.api_url + 'api/profil-statistique';

    constructor(
        protected http: HttpClient
    ) {
    }

    findByIdProfil(id: number): Observable<EntityArrayResponseType> {
        return this.http.get<ProfilStatistique[]>(`${this.resourceUrl}/${id}`, {observe: 'response'});
    }
}