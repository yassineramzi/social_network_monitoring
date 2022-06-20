import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';
import Societe from '@models/societe.model';

@Component({
  selector: 'app-societes-page',
  templateUrl: './societes-page.component.html',
  styleUrls: ['./societes-page.component.scss']
})
export class SocietesPageComponent implements OnInit {
  public paginationForm: FormGroup = this.formBuilder.group(
    {
      pageSize : new FormControl(6)
    }
  );

  public societesArrayPage: Array<Societe> = [];

  public societesPage: number = 1;

  public societesCollectionSize: number = 0;

  public pageSize: number;

  private societesArray: Array<Societe> = [];

  constructor(private formBuilder: FormBuilder) { }

  ngOnInit(): void {
  }

  public refreshSocietes(): void {
    this.pageSize = this.pageSizeFormControl.value;
    this.societesArrayPage = this.societesArray
      .slice((this.societesPage - 1) * this.pageSize, (this.societesPage - 1) * this.pageSize + this.pageSize);
    this.societesCollectionSize = this.societesArray.length;
  }

  public openModal(societe?: Societe): void {
    /*
    const modalRef = this.modalService.open(AjoutSocieteModalComponent, {size: 'md'});
    if (societe) {
      modalRef.componentInstance.societe = societe;
    }
    modalRef.result.then(
      (result: Societe) => {
        if (result) {
          this.addSociete(result);
        }
      }
    );*/
  }

  public editSociete(idSociete: number): void {
    /*
    let societe: Societe = this.societesArray.filter(
      societeTemp => societeTemp.id === idSociete
    )[0];
    if ( societe !== null ) {
      this.openModal(societe);
    }*/
  }

  public deleteSociete(idSociete: number): void {
    /*
    let societe: Societe = this.societesArray.filter(
      societeTemp => societeTemp.id === idSociete
    )[0];
    this.confirmationPopupServcie.confirm(
      'Confirmer',
      'Voulez vous supprimer la societe '+societe.nom+' ?'
    ).then(
      (confirmed) => {
        if(confirmed === true ) {
          this.societeSocialService.delete(idSociete).subscribe(
            (response) => {
              let societeSupprime: Societe = this.societesArray.splice(this.societesArray.indexOf(societe),1)[0];
              this.refreshSocietes();
              this.toastService.success('Le societe '+societeSupprime.nom+' a été supprimé', 'Suppression du societe');
            }
          );
        }
      }
    );*/
  }

  public get pageSizeFormControl(): FormControl {
    return this.paginationForm.get('pageSize') as FormControl;
  }

  private addSociete(societe: Societe): void {
    /*
    let index: number = this.societesArray.findIndex( societeTemp => {
      return societeTemp.id === societe.id;
    });
    if(index === -1){
      this.societesArray.push(societe);
    } else {
      this.societesArray[index].nom = societe.nom;
      this.societesArray[index].categorie = societe.categorie;
    }
    this.refreshSocietes();*/
  }
}
