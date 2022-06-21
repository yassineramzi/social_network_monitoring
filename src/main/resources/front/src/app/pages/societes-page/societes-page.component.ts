import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';
import Societe from '@models/societe.model';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ConfirmationPopupService } from '@services/confirmationPopup.service';
import SocieteService from '@services/societe.service';
import { ToastrService } from 'ngx-toastr';
import { AjoutSocietesModalComponent } from './ajout-societes-modal/ajout-societes-modal.component';

@Component({
  selector: 'app-societes-page',
  templateUrl: './societes-page.component.html',
  styleUrls: ['./societes-page.component.scss']
})
export class SocietesPageComponent {
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

  constructor(
    protected formBuilder: FormBuilder,
    protected modalService: NgbModal,
    protected confirmationPopupService: ConfirmationPopupService,
    protected toastService: ToastrService,
    protected societeService: SocieteService
    ) { }

  public refreshSocietes(): void {
    this.pageSize = this.pageSizeFormControl.value;
    this.societesArrayPage = this.societesArray
      .slice((this.societesPage - 1) * this.pageSize, (this.societesPage - 1) * this.pageSize + this.pageSize);
    this.societesCollectionSize = this.societesArray.length;
  }

  public openModal(): void {
    const modalRef = this.modalService.open(AjoutSocietesModalComponent, {size: 'md'});
    modalRef.result.then(
      (result: Societe) => {
        if (result) {
          this.addSociete(result);
        }
      }
    );
  }

  public deleteSociete(idSociete: number): void {
    let societe: Societe = this.societesArray.filter(
      societeTemp => societeTemp.id === idSociete
    )[0];
    this.confirmationPopupService.confirm(
      'Confirmer',
      'Voulez vous supprimer la societe '+societe.nom+' ?'
    ).then(
      (confirmed) => {
        if(confirmed === true ) {
          this.societeService.delete(idSociete).subscribe(
            (response) => {
              let societeSupprime: Societe = this.societesArray.splice(this.societesArray.indexOf(societe),1)[0];
              this.refreshSocietes();
              this.toastService.success('Le societe '+societeSupprime.nom+' a été supprimé', 'Suppression du societe');
            }
          );
        }
      }
    );
  }

  public get pageSizeFormControl(): FormControl {
    return this.paginationForm.get('pageSize') as FormControl;
  }

  private addSociete(societe: Societe): void {
    let index: number = this.societesArray.findIndex( societeTemp => {
      return societeTemp.id === societe.id;
    });
    if(index === -1){
      this.societesArray.push(societe);
    } else {
      this.societesArray[index].nom = societe.nom;
    }
    this.refreshSocietes();
  }
}
