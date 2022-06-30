import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import Societe  from '@models/societe.model';
import { environment } from "@environments/environment";

type EntityResponseType = HttpResponse<Societe>;
type EntityArrayResponseType = HttpResponse<Societe[]>;

@Injectable({
    providedIn: 'root'
})
export class SocieteService {

    public resourceUrl = environment.api_url + 'api/societe';

    constructor(
        protected http: HttpClient
    ) {
    }

    create(societe: Societe): Observable<EntityResponseType> {
        return this.http.post<Societe>(this.resourceUrl + '/create', societe, {observe: 'response'});
    }

    findAll(): Observable<EntityArrayResponseType> {
        return this.http.get<Societe[]>(`${this.resourceUrl}/all`, {observe: 'response'});
    }

    findById(id: number): Observable<EntityResponseType> {
        return this.http.get<Societe>(`${this.resourceUrl}/${id}`, {observe: 'response'});
    }

    delete(id: number): Observable<HttpResponse<void>>{
        return this.http.delete<void>(this.resourceUrl + "/delete?id="+id, {observe: 'response'});
    }
}