import { HttpResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { forkJoin } from 'rxjs';

import { ProfilService } from '@services/profil.service';
import { ProfilStatistiqueService } from '@services/profilStatistique.service';

import { ProfilStatistique } from '@models/profilStatistique.model';
import { Profil } from '@models/profil.model';


@Component({
  selector: 'app-profils-statistiques',
  templateUrl: './profils-statistiques-page.component.html',
  styleUrls: ['./profils-statistiques-page.component.scss']
})
export class ProfilsStatistiquesPageComponent implements OnInit {

  public paginationForm: FormGroup = this.formBuilder.group(
    {
      pageSize : new FormControl(5)
    }
  );

  public profilsStatistiquesArrayPage: Array<ProfilStatistique> = [];

  public profilsStatistiquesPage: number = 1;

  public profilsStatistiquesCollectionSize: number = 0;

  public pageSize: number;

  public profil: Profil = new Profil();

  private idProfil: number;

  private profilsStatistiquesArray: Array<ProfilStatistique> = [];

  constructor(
      protected formBuilder: FormBuilder,
      protected profilService: ProfilService,
      protected profilStatistiqueService: ProfilStatistiqueService,
      protected activatedRoute : ActivatedRoute
    ) { }

  public ngOnInit(): void {
    this.activatedRoute.params.subscribe(
      (params) => {
        if( params ['idProfil'] )
        {
          this.idProfil = params ['idProfil'];
          forkJoin([
            this.profilService.findById(this.idProfil),
            this.profilStatistiqueService.findByIdProfil(this.idProfil)
          ]).subscribe(
            (results: any) => {
              this._onProfilSuccess(results[0]);
              this._onProfilsStatistiquesSuccess(results[1]);
            },
            (error: any) => {
              this.profil = new Profil();
            }
          );
        }
      }
    );
  }

  public refreshProfilsStatistiques(): void {
    this.pageSize = this.pageSizeFormControl.value;
    this.profilsStatistiquesArrayPage = this.profilsStatistiquesArray
      .slice((this.profilsStatistiquesPage - 1) * this.pageSize, (this.profilsStatistiquesPage - 1) * this.pageSize + this.pageSize);
    this.profilsStatistiquesCollectionSize = this.profilsStatistiquesArray.length;
  }

  public get pageSizeFormControl(): FormControl {
    return this.paginationForm.get('pageSize') as FormControl;
  }

  private _onProfilSuccess(response: HttpResponse<Profil>): void {
    this.profil = response.body;
  }

  private _onProfilsStatistiquesSuccess(response: HttpResponse<ProfilStatistique[]>): void {
    this.profilsStatistiquesArray = response.body || [];
    this.refreshProfilsStatistiques();
  }
}
