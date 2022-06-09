import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { ProfilStatistique } from '@models/profilStatistique.model'; 
import { environment } from "@environments/environment";
import StatisticSet from '@models/statisticSet.model';

@Injectable({
    providedIn: 'root'
})
export class ProfilStatistiqueService {

    public resourceUrl = environment.api_url + 'api/profil-statistique';

    constructor(
        protected http: HttpClient
    ) {
    }

    public findByIdProfil(id: number): Observable<HttpResponse<ProfilStatistique[]>> {
        return this.http.get<ProfilStatistique[]>(`${this.resourceUrl}/${id}`, {observe: 'response'});
    }

    public findAllYoutubeViewsStatisticSet(): Observable<HttpResponse<Array<StatisticSet>>> {
        return this.http.get<Array<StatisticSet>>(`${this.resourceUrl}/youtube/views`, {observe: 'response'});
    }

    public findAllYoutubeSubscribersStatisticSet(): Observable<HttpResponse<Array<StatisticSet>>> {
        return this.http.get<Array<StatisticSet>>(`${this.resourceUrl}/youtube/subscribers`, {observe: 'response'});
    }

    public findAllTwitterFollowersStatisticSet(): Observable<HttpResponse<Array<StatisticSet>>> {
        return this.http.get<Array<StatisticSet>>(`${this.resourceUrl}/twitter/followers`, {observe: 'response'});
    }

    public findAllInstagramFollowersStatisticSet(): Observable<HttpResponse<Array<StatisticSet>>> {
        return this.http.get<Array<StatisticSet>>(`${this.resourceUrl}/instagram/followers`, {observe: 'response'});
    }
}