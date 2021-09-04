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

@Component({
  selector: 'app-profils',
  templateUrl: './profils-page.component.html',
  styleUrls: ['./profils-page.component.scss']
})
export class ProfilsPageComponent implements OnInit {

  public paginationForm: FormGroup = this.formBuilder.group(
    {
      pageSize : new FormControl(2)
    }
  );

  public profilsArrayPage: Array<Profil> = [];

  public profilsPage: number = 1;

  public profilsCollectionSize: number = 0;

  public pageSize: number = 4;

  private idDossier: number;

  private profilsArray: Array<Profil> = [];

  constructor(
      protected formBuilder: FormBuilder,
      protected profilService: ProfilService,
      protected modalService: NgbModal,
      protected confirmationPopupServcie: ConfirmationPopupService,
      protected activatedRoute : ActivatedRoute,
      protected toastService: ToastrService
    ) { }

  public ngOnInit(): void {
    this.activatedRoute.params.subscribe(
      (params) => {
        if( params ['idDossier'] )
        {
          this.idDossier = params ['idDossier'];
          this.profilService.findByIdDossier(params ['idDossier']).subscribe(
            (response: HttpResponse<Profil[]>) => {
              this.profilsArray = response.body || [];
              this.refreshProfils();
            }
          );
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
          this.addProfil(result);
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
    this.confirmationPopupServcie.confirm(
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

  private addProfil(profil: Profil): void {
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
}
