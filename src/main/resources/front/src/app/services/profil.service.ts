import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { Profil } from '@models/profil.model';
import { environment } from "@environments/environment";

type EntityResponseType = HttpResponse<Profil>;
type EntityArrayResponseType = HttpResponse<Profil[]>;

@Injectable({
    providedIn: 'root'
})
export class ProfilService {

    public resourceUrl = environment.api_url + 'api/profil';

    constructor(
        protected http: HttpClient
    ) {
    }

    create(profil: Profil): Observable<EntityResponseType> {
        return this.http.post<Profil>(this.resourceUrl + '/create', profil, {observe: 'response'});
    }

    update(profil: Profil): Observable<EntityResponseType> {
        return this.http.put<Profil>(this.resourceUrl + '/update', profil, {observe: 'response'});
    }

    findByIdDossier(id: number): Observable<EntityArrayResponseType> {
        return this.http.get<Profil[]>(`${this.resourceUrl}/${id}`, {observe: 'response'});
    }

    delete(id: number): Observable<HttpResponse<void>>{
        return this.http.delete<void>(this.resourceUrl + "/delete?id="+id, {observe: 'response'});
    }
}