import { HttpResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';

import { DossierSocial } from '@models/dossierSocial.model';
import { ECategorie } from '@models/enum/ecategorie.enum';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { DossierSocialService } from '@services/dossierSocial.service';
import { ConfirmationPopupService } from "@services/confirmationPopup.service";
import { AjoutDossierModalComponent } from './ajout-dossier-modal/ajout-dossier-modal.component';

@Component({
  selector: 'app-dossiers-sociaux',
  templateUrl: './dossiers-sociaux-page.component.html',
  styleUrls: ['./dossiers-sociaux-page.component.scss']
})
export class DossiersSociauxPageComponent implements OnInit {

  public categories = ECategorie;

  public paginationForm: FormGroup = this.formBuilder.group(
    {
      pageSize : new FormControl(2)
    }
  );

  public dossiersArrayPage: Array<DossierSocial> = [];

  public dossiersPage: number = 1;

  public dossiersCollectionSize: number = 0;

  public pageSize: number = 4;

  private dossiersArray: Array<DossierSocial> = [];

  constructor(
      protected formBuilder: FormBuilder,
      protected dossierSocialService: DossierSocialService,
      protected modalService: NgbModal,
      protected confirmationPopupServcie: ConfirmationPopupService
    ) { }

  public ngOnInit(): void {
    this.dossierSocialService.findAll().subscribe(
      (response: HttpResponse<DossierSocial[]>) => {
        this.dossiersArray = response.body || [];
        this.refreshDossiers();
      }
    );
  }

  public refreshDossiers(): void {
    this.pageSize = this.pageSizeFormControl.value;
    this.dossiersArrayPage = this.dossiersArray
      .slice((this.dossiersPage - 1) * this.pageSize, (this.dossiersPage - 1) * this.pageSize + this.pageSize);
    this.dossiersCollectionSize = this.dossiersArray.length;
  }

  public openModal(dossier?: DossierSocial): void {
    const modalRef = this.modalService.open(AjoutDossierModalComponent, {size: 'md'});
    if (dossier) {
      modalRef.componentInstance.dossier = dossier;
    }
    modalRef.result.then(
      (result: DossierSocial) => {
        if (result) {
          this.addDossier(result);
        }
      }
    );
  }

  public editDossier(idDossier: number): void {
    let dossier: DossierSocial = this.dossiersArray.filter(
      dossierTemp => dossierTemp.id === idDossier
    )[0];
    if ( dossier !== null ) {
      this.openModal(dossier);
    }
  }

  public deleteDossier(idDossier: number): void {
    let dossier: DossierSocial = this.dossiersArray.filter(
      dossierTemp => dossierTemp.id === idDossier
    )[0];
    this.confirmationPopupServcie.confirm(
      'Confirmer',
      'Voulez vous supprimer le dossier '+dossier.nom+' ?'
    ).then(
      (confirmed) => {
        if(confirmed === true ) {
          this.dossierSocialService.delete(idDossier).subscribe(
            (response) => {
              this.dossiersArray.splice(this.dossiersArray.indexOf(dossier),1);
              this.refreshDossiers();
            }
          );
        }
      }
    );
  }

  public get pageSizeFormControl(): FormControl {
    return this.paginationForm.get('pageSize') as FormControl;
  }

  private addDossier(dossier: DossierSocial): void {
    let index: number = this.dossiersArray.findIndex( dossierTemp => {
      return dossierTemp.id === dossier.id;
    });
    if(index === -1){
      this.dossiersArray.push(dossier);
    } else {
      this.dossiersArray[index].nom = dossier.nom;
      this.dossiersArray[index].categorie = dossier.categorie;
    }
    this.refreshDossiers();
  }
}
