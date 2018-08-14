/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { Starkev2TestModule } from '../../../test.module';
import { StarkeUsersMySuffixDeleteDialogComponent } from 'app/entities/starke-users-my-suffix/starke-users-my-suffix-delete-dialog.component';
import { StarkeUsersMySuffixService } from 'app/entities/starke-users-my-suffix/starke-users-my-suffix.service';

describe('Component Tests', () => {
    describe('StarkeUsersMySuffix Management Delete Component', () => {
        let comp: StarkeUsersMySuffixDeleteDialogComponent;
        let fixture: ComponentFixture<StarkeUsersMySuffixDeleteDialogComponent>;
        let service: StarkeUsersMySuffixService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [Starkev2TestModule],
                declarations: [StarkeUsersMySuffixDeleteDialogComponent]
            })
                .overrideTemplate(StarkeUsersMySuffixDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(StarkeUsersMySuffixDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(StarkeUsersMySuffixService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete', inject(
                [],
                fakeAsync(() => {
                    // GIVEN
                    spyOn(service, 'delete').and.returnValue(of({}));

                    // WHEN
                    comp.confirmDelete(123);
                    tick();

                    // THEN
                    expect(service.delete).toHaveBeenCalledWith(123);
                    expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                })
            ));
        });
    });
});
