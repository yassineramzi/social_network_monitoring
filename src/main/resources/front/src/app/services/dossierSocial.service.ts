import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { DossierSocial } from '@models/dossierSocial.model';
import { environment } from "@environments/environment";

type EntityResponseType = HttpResponse<DossierSocial>;
type EntityArrayResponseType = HttpResponse<DossierSocial[]>;

@Injectable({
    providedIn: 'root'
})
export class DossierSocialService {

    public resourceUrl = environment.api_url + 'api/dossier';

    constructor(
        protected http: HttpClient
    ) {
    }

    create(dossierSocial: DossierSocial): Observable<EntityResponseType> {
        return this.http.post<DossierSocial>(this.resourceUrl + '/create', dossierSocial, {observe: 'response'});
    }

    update(dossierSocial: DossierSocial): Observable<EntityResponseType> {
        return this.http.put<DossierSocial>(this.resourceUrl + '/update', dossierSocial, {observe: 'response'});
    }

    findAll(): Observable<EntityArrayResponseType> {
        return this.http.get<DossierSocial[]>(this.resourceUrl + '/all', {observe: 'response'});
    }

    delete(id: number): Observable<HttpResponse<void>>{
        return this.http.delete<void>(this.resourceUrl + "/delete?id="+id, {observe: 'response'});
    }
}