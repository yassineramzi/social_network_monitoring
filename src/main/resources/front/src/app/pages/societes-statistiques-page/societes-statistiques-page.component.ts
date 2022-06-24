import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';
import SocieteStatistique from '@models/societeStatistique.model';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import SocieteService from '@services/societe.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-societes-statistiques-page',
  templateUrl: './societes-statistiques-page.component.html',
  styleUrls: ['./societes-statistiques-page.component.scss']
})
export class SocietesStatistiquesPageComponent implements OnInit {

  public paginationForm: FormGroup = this.formBuilder.group(
    {
      pageSize : new FormControl(6)
    }
  );

  public societesStatistiquesArrayPage: Array<SocieteStatistique> = [];

  public societesPage: number = 1;

  public societesCollectionSize: number = 0;

  public pageSize: number;

  private societesStatistiquesArray: Array<SocieteStatistique> = [];

  constructor(
    protected formBuilder: FormBuilder,
    protected modalService: NgbModal,
    protected toastService: ToastrService,
    protected societeStatistiqueService: SocieteService
    ) { }

  public refreshSocietesStatistiques(): void {
    this.pageSize = this.pageSizeFormControl.value;
    this.societesStatistiquesArrayPage = this.societesStatistiquesArray
      .slice((this.societesPage - 1) * this.pageSize, (this.societesPage - 1) * this.pageSize + this.pageSize);
    this.societesCollectionSize = this.societesStatistiquesArray.length;
  }

  public openModal(): void {
    /*const modalRef = this.modalService.open(AjoutSocietesModalComponent, {size: 'md'});
    modalRef.result.then(
      (result: SocieteStatistique) => {
        if (result) {
          this.addSocieteStatistique(result);
        }
      }
    );*/
  }

  public get pageSizeFormControl(): FormControl {
    return this.paginationForm.get('pageSize') as FormControl;
  }

  private addSocieteStatistique(societeStatistique: SocieteStatistique): void {
    let index: number = this.societesStatistiquesArray.findIndex( societeStatistiqueTemp => {
      return societeStatistiqueTemp.id === societeStatistique.id;
    });
    if(index === -1){
      this.societesStatistiquesArray.push(societeStatistique);
    } else {
      this.societesStatistiquesArray[index].prixGazoil = societeStatistique.prixGazoil;
      this.societesStatistiquesArray[index].prixEssence = societeStatistique.prixEssence;
    }
    this.refreshSocietesStatistiques();
  }

}
