import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { ProfilStatistique } from '@models/profilStatistique.model'; 
import StatisticData from '@models/statisticData.model';
import { environment } from "@environments/environment";

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

    public findAllYoutubeViewsStatisticSet(): Observable<HttpResponse<StatisticData>> {
        return this.http.get<StatisticData>(`${this.resourceUrl}/youtube/views`, {observe: 'response'});
    }

    public findAllYoutubeSubscribersStatisticSet(): Observable<HttpResponse<StatisticData>> {
        return this.http.get<StatisticData>(`${this.resourceUrl}/youtube/subscribers`, {observe: 'response'});
    }

    public findAllTwitterFollowersStatisticSet(): Observable<HttpResponse<StatisticData>> {
        return this.http.get<StatisticData>(`${this.resourceUrl}/twitter/followers`, {observe: 'response'});
    }

    public findAllInstagramFollowersStatisticSet(): Observable<HttpResponse<StatisticData>> {
        return this.http.get<StatisticData>(`${this.resourceUrl}/instagram/followers`, {observe: 'response'});
    }
}