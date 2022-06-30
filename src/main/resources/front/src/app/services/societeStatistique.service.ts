import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { environment } from "@environments/environment";
import SocieteStatistique  from '@models/societeStatistique.model';

type EntityResponseType = HttpResponse<SocieteStatistique>;
type EntityArrayResponseType = HttpResponse<SocieteStatistique[]>;

@Injectable({
    providedIn: 'root'
})
export class SocieteStatistiquesService {

    public resourceUrl = environment.api_url + 'api/societe-statistiques';

    constructor(
        protected http: HttpClient
    ) {
    }

    create(societeStatistique: SocieteStatistique): Observable<EntityResponseType> {
        return this.http.post<SocieteStatistique>(this.resourceUrl + '/create', societeStatistique, {observe: 'response'});
    }

    findByIdSociete(idSociete: number): Observable<EntityArrayResponseType> {
        return this.http.get<SocieteStatistique[]>(`${this.resourceUrl}/${idSociete}/societe`, {observe: 'response'});
    }
}