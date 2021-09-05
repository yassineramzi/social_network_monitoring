import { HttpResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';

import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ProfilService } from '@services/profil.service';
import { ConfirmationPopupService } from "@services/confirmationPopup.service";
import { Profil } from "@models/profil.model";
import { AjoutProfilModalComponent } from './ajout-profil-modal/ajout-profil-modal.component';
import { ActivatedRoute } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { forkJoin } from 'rxjs';
import { DossierSocial } from '@models/dossierSocial.model';
import { DossierSocialService } from '@services/dossierSocial.service';
import { SelectDossierModalComponent } from './select-dossier-modal/select-dossier-modal.component';

@Component({
  selector: 'app-profils',
  templateUrl: './profils-page.component.html',
  styleUrls: ['./profils-page.component.scss']
})
export class ProfilsPageComponent implements OnInit {

  public paginationForm: FormGroup = this.formBuilder.group(
    {
      pageSize : new FormControl(6)
    }
  );

  public profilsArrayPage: Array<Profil> = [];

  public profilsPage: number = 1;

  public profilsCollectionSize: number = 0;

  public pageSize: number;

  public idDossier: number;

  public dossier: DossierSocial = new DossierSocial();

  private profilsArray: Array<Profil> = [];

  constructor(
      protected formBuilder: FormBuilder,
      protected profilService: ProfilService,
      protected dossierService: DossierSocialService,
      protected modalService: NgbModal,
      protected confirmationPopupService: ConfirmationPopupService,
      protected toastService: ToastrService,
      protected activatedRoute : ActivatedRoute,
    ) { }

  public ngOnInit(): void {
    this.activatedRoute.params.subscribe(
      (params) => {
        if( params ['idDossier'] )
        {
          this.idDossier = params ['idDossier'];
          forkJoin(
            [
              this.profilService.findByIdDossier(this.idDossier),
              this.dossierService.findById(this.idDossier)
            ]
          )
          .subscribe(
            (results: any) => {
              this._onProfilsSuccess(results[0]);
              this._onDossierSuccess(results[1]);
            },
            (error: any) => {
              this.dossier = new DossierSocial();
            }
          );
        } else {
          this._openSelectDossierModal();
        }
      }
    );
  }

  public refreshProfils(): void {
    this.pageSize = this.pageSizeFormControl.value;
    this.profilsArrayPage = this.profilsArray
      .slice((this.profilsPage - 1) * this.pageSize, (this.profilsPage - 1) * this.pageSize + this.pageSize);
    this.profilsCollectionSize = this.profilsArray.length;
  }

  public openModal(profil?: Profil): void {
    const modalRef = this.modalService.open(AjoutProfilModalComponent, {size: 'md'});
    if (profil) {
      modalRef.componentInstance.profil = profil;
    }
    modalRef.componentInstance.idDossier = this.idDossier;
    modalRef.result.then(
      (result: Profil) => {
        if (result) {
          this._addProfil(result);
        }
      }
    );
  }

  public editProfil(idProfil: number): void {
    let profil: Profil = this.profilsArray.filter(
      profilTemp => profilTemp.id === idProfil
    )[0];
    if ( profil !== null ) {
      this.openModal(profil);
    }
  }

  public deleteProfil(idProfil: number): void {
    let profil: Profil = this.profilsArray.filter(
      profilTemp => profilTemp.id === idProfil
    )[0];
    this.confirmationPopupService.confirm(
      'Confirmer',
      'Voulez vous supprimer le profil '+profil.nom+' ?'
    ).then(
      (confirmed) => {
        if(confirmed === true ) {
          this.profilService.delete(idProfil).subscribe(
            (response) => {
              let profilSupprime: Profil = this.profilsArray.splice(this.profilsArray.indexOf(profil),1)[0];
              this.refreshProfils();
              this.toastService.success('Le profil '+profilSupprime.nom+' a été supprimé', 'Suppression du profil');
            }
          );
        }
      }
    );
  }

  public get pageSizeFormControl(): FormControl {
    return this.paginationForm.get('pageSize') as FormControl;
  }

  private _addProfil(profil: Profil): void {
    let index: number = this.profilsArray.findIndex( profilTemp => {
      return profilTemp.id === profil.id;
    });
    if(index === -1){
      this.profilsArray.push(profil);
    } else {
      this.profilsArray[index] = profil;
    }
    this.refreshProfils();
  }

  private _onProfilsSuccess(response: HttpResponse<Profil[]>): void{
    this.profilsArray = response.body || [];
    this.refreshProfils();
  }

  private _onDossierSuccess(response: HttpResponse<DossierSocial>): void{
    this.dossier = response.body;
  }

  private _openSelectDossierModal(): void {
    this.modalService.open(SelectDossierModalComponent, {keyboard: false, backdrop: 'static', size: 'md'});
  }
}
