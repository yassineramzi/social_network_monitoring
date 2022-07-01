import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { environment } from "@environments/environment";
import SocieteStatistique  from '@models/societeStatistique.model';
import StatisticSet from '@models/statisticSet.model';

type EntityResponseType = HttpResponse<SocieteStatistique>;
type EntityArrayResponseType = HttpResponse<SocieteStatistique[]>;

@Injectable({
    providedIn: 'root'
})
export class SocieteStatistiquesService {

    private resourceUrl = environment.api_url + 'api/societe-statistiques';

    constructor(
        protected http: HttpClient
    ) {
    }

    public create(societeStatistique: SocieteStatistique): Observable<EntityResponseType> {
        return this.http.post<SocieteStatistique>(this.resourceUrl + '/create', societeStatistique, {observe: 'response'});
    }

    public findByIdSociete(idSociete: number): Observable<EntityArrayResponseType> {
        return this.http.get<SocieteStatistique[]>(`${this.resourceUrl}/${idSociete}/societe`, {observe: 'response'});
    }

    public findAllGazoilStatisticSet(): Observable<HttpResponse<Array<StatisticSet>>> {
        return this.http.get<Array<StatisticSet>>(`${this.resourceUrl}/gazoil-statistics`, {observe: 'response'});
    }

    public findAllEssenceStatisticSet(): Observable<HttpResponse<Array<StatisticSet>>> {
        return this.http.get<Array<StatisticSet>>(`${this.resourceUrl}/essence-statistics`, {observe: 'response'});
    }
}